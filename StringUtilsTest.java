package testing;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidParameterException;

/**
 * JUnit 4 tests for StringUtils.
 *
 * Tests are written to validate the behavior described in the source comments.
 * Where comments specify behavior, tests assert that behavior. If implementation
 * deviates, tests will fail and produce clear messages mapping failures to defects.
 *
 * NOTE: This test uses reflection to:
 *  - instantiate the class under test (StringUtils)
 *  - inspect private field myStr
 *  - invoke updateToConcat(String)
 *
 * If StringUtils is declared in a package other than the default package,
 * update the class name in Class.forName(...) below to the fully-qualified name.
 */
public class StringUtilsTest {

    private Class<?> sutClass;

    @Before
    public void setUp() throws Exception {
        // The StringUtils class in the provided source is in the default package.
        // If it exists in a package, set the fully-qualified name here (e.g., "utils.StringUtils").
        sutClass = Class.forName("StringUtils");
    }

    // Helper to construct StringUtils
    private Object newStringUtils(String init) throws Exception {
        Constructor<?> ctor = sutClass.getConstructor(String.class);
        return ctor.newInstance(init);
    }

    // Helper to get myStr
    private String getMyStr(Object instance) throws Exception {
        Field f = sutClass.getDeclaredField("myStr");
        f.setAccessible(true);
        Object val = f.get(instance);
        return (String) val;
    }

    // Helper to call updateToConcat
    private String callUpdateToConcat(Object instance, String arg) throws Throwable {
        Method m = sutClass.getMethod("updateToConcat", String.class);
        try {
            Object ret = m.invoke(instance, arg);
            return (String) ret;
        } catch (InvocationTargetException ite) {
            // Re-throw the cause to allow tests to assert on exact exception type
            throw ite.getCause();
        }
    }

    // T01: Constructor with valid string should set myStr
    @Test
    public void constructor_validString_setsMyStr() throws Exception {
        Object sut = newStringUtils("hello");
        assertEquals("Constructor should set myStr to the provided value", "hello", getMyStr(sut));
    }

    // T02: Constructor with empty string should throw NullPointerException (per comments)
    @Test
    public void constructor_emptyString_throwsNullPointerException() throws Exception {
        try {
            newStringUtils("");
            fail("Constructor accepted empty string but comments specify that it should throw NullPointerException");
        } catch (InvocationTargetException ite) {
            // invocation wrapper — unwrap
            Throwable cause = ite.getCause();
            assertTrue("Expected NullPointerException for empty constructor param; got: " +
                       cause.getClass().getName(), cause instanceof NullPointerException);
        }
    }

    // T03: Constructor with null should throw NullPointerException (per comments)
    @Test
    public void constructor_null_throwsNullPointerException() throws Exception {
        try {
            // Use reflective newInstance with explicit null object to select correct overload
            Constructor<?> ctor = sutClass.getConstructor(String.class);
            ctor.newInstance((Object) null);
            fail("Constructor accepted null but comments specify that it should throw NullPointerException");
        } catch (InvocationTargetException ite) {
            Throwable cause = ite.getCause();
            assertTrue("Expected NullPointerException for null constructor param; got: " +
                       cause.getClass().getName(), cause instanceof NullPointerException);
        }
    }

    // T04: updateToConcat with valid parameter should return concatenated value and update myStr
    @Test
    public void updateToConcat_valid_appendsAndReturnsNewValue() throws Throwable {
        Object sut = newStringUtils("base");
        String returned = callUpdateToConcat(sut, "X");
        assertEquals("updateToConcat should return concatenated string", "baseX", returned);
        assertEquals("myStr should be updated to concatenated string", "baseX", getMyStr(sut));
    }

    // T05: updateToConcat with empty string should throw InvalidParameterException (per comments)
    @Test
    public void updateToConcat_empty_throwsInvalidParameterException_perComments() throws Throwable {
        Object sut = newStringUtils("start");
        try {
            callUpdateToConcat(sut, "");
            fail("Expected InvalidParameterException for empty parameter (per comments), but method returned normally");
        } catch (Throwable t) {
            // If the implementation throws a different exception (e.g., NullPointerException),
            // fail explicitly and provide a helpful message to identify the mismatch.
            if (t instanceof InvalidParameterException) {
                // PASS — matches comment-spec
            } else {
                fail("Expected InvalidParameterException for empty parameter, but got " +
                     t.getClass().getName() + " with message: " + t.getMessage());
            }
        }
    }

    // T06: updateToConcat with null should throw InvalidParameterException (per comments)
    @Test
    public void updateToConcat_null_throwsInvalidParameterException_perComments() throws Throwable {
        Object sut = newStringUtils("start");
        try {
            callUpdateToConcat(sut, null);
            fail("Expected InvalidParameterException for null parameter (per comments), but method returned normally");
        } catch (Throwable t) {
            if (t instanceof InvalidParameterException) {
                // PASS — matches comment-spec
            } else {
                fail("Expected InvalidParameterException for null parameter, but got " +
                     t.getClass().getName() + " with message: " + t.getMessage());
            }
        }
    }
}
