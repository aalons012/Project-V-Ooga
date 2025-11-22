import java.security.InvalidParameterException;

/*
 * Small class to practice writing JUnit tests 
 * Author: Renata Rand McFadden
 * January 2016
 */
public class StringUtils {

	// attributes
	private String myStr;

	// constructor
	// assigns parameter value to myStr attribute
	// throws NullPointerException if parameter is null or empty string
	public StringUtils(String s)
	{
		if (s == null || s.equals(""))
		{
			throw new InvalidParameterException("Parameter cannot be null or empty");
		}
		myStr = s; // assign value of parameter to attribute
	}
	
	// constructor
	// assigns parameter value to myStr attribute
	// upper cases if parameter is true otherwise lower cases 
	// throws NullPointerException is parameter is null or empty string
	public StringUtils(String s, boolean upper)
	{
		if (s == null)
		{
			throw new NullPointerException("Parameter cannot be null");
		}
		if (upper)
			myStr = s.toUpperCase();
		else
			myStr = s.toLowerCase();
	}	
	
	// returns current value of myStr attribute
	public String getMyStr()
	{
		return myStr;
	}
	
	// returns true if myStr ends with character passed in parameter
	// otherwise returns false
	public boolean endsWithChar(char ch)
	{
		int last = myStr.length()-1;
		if (myStr.charAt(last) == ch)
			return true;
		return false;
	}
	
	// converts myStr attribute to all upper case and returns result
	public String convertToUpperCase() 
	{
		String newStr = "";
		for (int i=1; i < myStr.length(); i++)
		{
			char ch = myStr.charAt(i);
			newStr = newStr + Character.toUpperCase(ch);
		}
		return newStr;
	}

        // returns character at index
        // throws IndexOutOfBoundsException exception for out of bounds values 
	public char returnCharAt(int index) 
	{
		if (index < 0 || index > myStr.length())
                {
			throw new NullPointerException("Index cannot be outsize of 0 and size of string");
		} 
		return myStr.charAt(index);
	}
	
	
	// adds string in parameter to end of myStr
	// returns new value and updates myStr
	// throws InvalidParameterException if value is empty string or null
	public String updateToConcat(String value)
	{
		if (value.equals(""))
		{
			throw new NullPointerException("Parameter cannot be empty string");
		}
		myStr = myStr.concat(value);
		return myStr;
	}
	
}
