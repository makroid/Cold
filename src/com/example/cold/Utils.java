package com.example.cold;

public class Utils {
	public static boolean isDouble(String value)
	{        
	    boolean seenDot = false;
	    boolean seenExp = false;
	    boolean justSeenExp = false;
	    boolean seenDigit = false;
	    for (int i=0; i < value.length(); i++)
	    {
	        char c = value.charAt(i);
	        if (c >= '0' && c <= '9')
	        {
	            seenDigit = true;
	            continue;
	        }
	        if ((c == '-' || c=='+') && (i == 0 || justSeenExp))
	        {
	            continue;
	        }
	        if (c == '.' && !seenDot)
	        {
	            seenDot = true;
	            continue;
	        }
	        justSeenExp = false;
	        if ((c == 'e' || c == 'E') && !seenExp)
	        {
	            seenExp = true;
	            justSeenExp = true;
	            continue;
	        }
	        return false;
	    }
	    if (!seenDigit)
	    {
	        return false;
	    }
	    try
	    {
	        Double.parseDouble(value);
	        return true;
	    }
	    catch (NumberFormatException e)
	    {
	        return false;
	    }
	}
	
	public static boolean isFloat(String value)
	{        
	    boolean seenDot = false;
	    boolean seenExp = false;
	    boolean justSeenExp = false;
	    boolean seenDigit = false;
	    for (int i=0; i < value.length(); i++)
	    {
	        char c = value.charAt(i);
	        if (c >= '0' && c <= '9')
	        {
	            seenDigit = true;
	            continue;
	        }
	        if ((c == '-' || c=='+') && (i == 0 || justSeenExp))
	        {
	            continue;
	        }
	        if (c == '.' && !seenDot)
	        {
	            seenDot = true;
	            continue;
	        }
	        justSeenExp = false;
	        if ((c == 'e' || c == 'E') && !seenExp)
	        {
	            seenExp = true;
	            justSeenExp = true;
	            continue;
	        }
	        return false;
	    }
	    if (!seenDigit)
	    {
	        return false;
	    }
	    try
	    {
	        Float.parseFloat(value);
	        return true;
	    }
	    catch (NumberFormatException e)
	    {
	        return false;
	    }
	}

}
