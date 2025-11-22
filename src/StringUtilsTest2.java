import static org.junit.Assert.*;
import org.junit.Test;
import java.security.InvalidParameterException;

public class StringUtilsTest2 {

	// pass
    // 1. Single-argument constructor with valid non-empty string
    @Test
    public void testConstructor_singleArg_validNonEmptyString_setsMyStr() {
        StringUtils utils = new StringUtils("Hello");
        assertEquals("Hello", utils.getMyStr());
    }

    // fail
    // 2. Single-argument constructor with null should throw NullPointerException (per comments)
    @Test(expected = NullPointerException.class)
    public void testConstructor_singleArg_null_throwsNullPointerException() {
        new StringUtils(null);
    }

    // fail
    // 3. Single-argument constructor with empty string should throw NullPointerException (per comments)
    @Test(expected = NullPointerException.class)
    public void testConstructor_singleArg_emptyString_throwsNullPointerException() {
        new StringUtils("");
    }

    //pass
    // 4. Two-argument constructor upper=true converts to upper-case
    @Test
    public void testConstructor_twoArg_upperTrue_convertsToUpperCase() {
        StringUtils utils = new StringUtils("abc", true);
        assertEquals("ABC", utils.getMyStr());
    }
    
    // PASS
    // 5. Two-argument constructor upper=false converts to lower-case
    @Test
    public void testPASSConstructor_twoArg_upperFalse_convertsToLowerCase() {
        StringUtils utils = new StringUtils("AbC", false);
        assertEquals("abc", utils.getMyStr());
    }

    //PASS
    // 6. Two-argument constructor with null should throw NullPointerException (per comments)
    @Test(expected = NullPointerException.class)
    public void testConstructor_twoArg_nullString_throwsNullPointerException() {
        new StringUtils(null, true);
    }

    // FAIL
    // 7. Two-argument constructor with empty string should throw NullPointerException (per comments)
    @Test(expected = NullPointerException.class)
    public void testConstructor_twoArg_emptyString_throwsNullPointerException() {
        new StringUtils("", false);
    }
    
    // PASS
    // 8. getMyStr returns current value after construction and concatenation
    @Test
    public void testGetMyStr_returnsCurrentValueAfterConstruction() {
        StringUtils utils = new StringUtils("base");
        assertEquals("base", utils.getMyStr());
        utils.updateToConcat("X");
        assertEquals("baseX", utils.getMyStr());
    }

    // PASS
    // 9. endsWithChar returns true when last character matches
    @Test
    public void testEndsWithChar_trueWhenLastCharMatches() {
        StringUtils utils = new StringUtils("hello");
        assertTrue(utils.endsWithChar('o'));
    }

    // PASS
    // 10. endsWithChar returns false when last character does not match
    @Test
    public void testEndsWithChar_falseWhenLastCharDoesNotMatch() {
        StringUtils utils = new StringUtils("hello");
        assertFalse(utils.endsWithChar('a'));
    }

    // PASS
    // 11. endsWithChar comparison is case-sensitive
    @Test
    public void testEndsWithChar_isCaseSensitiveOnLastChar() {
        StringUtils utils = new StringUtils("HellO");
        // last character is 'O', so checking 'o' should be false
        assertFalse(utils.endsWithChar('o'));
    }

    // PASS
    // 12. endsWithChar works for single-character strings
    @Test
    public void testEndsWithChar_singleCharacterString() {
        StringUtils utils = new StringUtils("x");
        assertTrue(utils.endsWithChar('x'));
        assertFalse(utils.endsWithChar('y'));
    }

    // FAIL
    // 13. endsWithChar on empty string should be handled (we expect false per reasonable spec)
    @Test
    public void testEndsWithChar_onEmptyString_shouldReturnFalse() {
        // 2-arg constructor comments say it should reject null/empty, but code only checks null.
        StringUtils utils = new StringUtils("", false); // allowed by current implementation
        assertFalse(utils.endsWithChar('a'));           // current code will throw an exception here
    }

    // FAIL
    // 14. convertToUpperCase converts all-lowercase word to upper-case
    @Test
    public void testConvertToUpperCase_allLowercaseWord() {
        StringUtils utils = new StringUtils("hello");
        String result = utils.convertToUpperCase();
        assertEquals("HELLO", result);
    }

    // FAIL
    // 15. convertToUpperCase converts mixed-case word to all upper-case
    @Test
    public void testConvertToUpperCase_mixedCaseWord() {
        StringUtils utils = new StringUtils("HeLlo");
        String result = utils.convertToUpperCase();
        assertEquals("HELLO", result);
    }

    // FAIL
    // 16. convertToUpperCase leaves already upper-case word unchanged
    @Test
    public void testConvertToUpperCase_alreadyUppercaseWord() {
        StringUtils utils = new StringUtils("WORLD");
        String result = utils.convertToUpperCase();
        assertEquals("WORLD", result);
    }

    // FAIL
    // 17. convertToUpperCase handles single-character strings
    @Test
    public void testConvertToUpperCase_singleCharacter() {
        StringUtils utils = new StringUtils("z");
        String result = utils.convertToUpperCase();
        assertEquals("Z", result);
    }

    // FAIL
    // 18. convertToUpperCase handles two-character strings
    @Test
    public void testConvertToUpperCase_twoCharacterString() {
        StringUtils utils = new StringUtils("ab");
        String result = utils.convertToUpperCase();
        assertEquals("AB", result);
    }

    // FAIL
    // 19. convertToUpperCase should also update the myStr attribute (per comment text)
    @Test
    public void testConvertToUpperCase_updatesMyStrAttributeToUppercase() {
        StringUtils utils = new StringUtils("hello");
        String result = utils.convertToUpperCase();
        assertEquals("HELLO", result);
        // Comment says "converts myStr attribute to all upper case"
        assertEquals("HELLO", utils.getMyStr());
    }

    // PASS
    // 20. returnCharAt(0) returns the first character
    @Test
    public void testReturnCharAt_zeroIndexReturnsFirstChar() {
        StringUtils utils = new StringUtils("abc");
        assertEquals('a', utils.returnCharAt(0));
    }

    // PASS
    // 21. returnCharAt(lastIndex) returns the last character
    @Test
    public void testReturnCharAt_lastIndexReturnsLastChar() {
        StringUtils utils = new StringUtils("abc");
        assertEquals('c', utils.returnCharAt(2));
    }

    // FAIL
    // 22. returnCharAt with negative index should throw IndexOutOfBoundsException (per comments)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReturnCharAt_negativeIndex_throwsIndexOutOfBoundsException() {
        StringUtils utils = new StringUtils("abc");
        utils.returnCharAt(-1);
    }

    // FAIL
    // 23. returnCharAt with index greater than length should throw IndexOutOfBoundsException (per comments)
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReturnCharAt_indexGreaterThanLength_throwsIndexOutOfBoundsException() {
        StringUtils utils = new StringUtils("abc");
        utils.returnCharAt(5);
    }

    // PASS
    // 24. returnCharAt with index equal to length should throw IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void testReturnCharAt_indexEqualToLength_throwsIndexOutOfBoundsException() {
        StringUtils utils = new StringUtils("abc");
        utils.returnCharAt(3);
    }

    // PASS
    // 25. returnCharAt on middle index after concatenation
    @Test
    public void testReturnCharAt_middleIndexAfterConcatenation() {
        StringUtils utils = new StringUtils("ab");
        utils.updateToConcat("cd");      // "abcd"
        assertEquals('b', utils.returnCharAt(1));
    }

    // PASS
    // 26. updateToConcat with valid non-empty string appends at the end
    @Test
    public void testUpdateToConcat_validNonEmptyString_appendsToEnd() {
        StringUtils utils = new StringUtils("abc");
        String result = utils.updateToConcat("XYZ");
        assertEquals("abcXYZ", result);
        assertEquals("abcXYZ", utils.getMyStr());
    }

    // PASS
    // 27. updateToConcat called multiple times accumulates in order
    @Test
    public void testUpdateToConcat_multipleConcats_accumulateInOrder() {
        StringUtils utils = new StringUtils("start");
        utils.updateToConcat("_one");
        String result = utils.updateToConcat("_two");
        assertEquals("start_one_two", result);
    }

    // FAIL
    // 28. updateToConcat with empty string should throw InvalidParameterException (per comments)
    @Test(expected = InvalidParameterException.class)
    public void testUpdateToConcat_emptyString_throwsInvalidParameterException() {
        StringUtils utils = new StringUtils("abc");
        utils.updateToConcat("");
    }

    // FAIL
    // 29. updateToConcat with null should throw InvalidParameterException (per comments)
    @Test(expected = InvalidParameterException.class)
    public void testUpdateToConcat_nullString_throwsInvalidParameterException() {
        StringUtils utils = new StringUtils("abc");
        utils.updateToConcat(null);
    }

    // PASS
    // 30. If updateToConcat throws an exception, myStr should remain unchanged
    @Test
    public void testUpdateToConcat_doesNotChangeMyStrWhenExceptionThrown() {
        StringUtils utils = new StringUtils("base");
        try {
            utils.updateToConcat("");
            fail("Expected an exception to be thrown");
        } catch (RuntimeException ex) {
            // Any runtime exception is fine here; just checking state afterwards
        }
        assertEquals("base", utils.getMyStr());
    }
}

