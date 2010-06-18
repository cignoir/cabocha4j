package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author noire722
 *
 */
public class RegexParser {
	public static final char DOUBLE_QUATE = '\"';
	
	/**
	 * Find and get the inner string between the specified symbol in
	 * constructor's argument.If not found, return null.
	 * 
	 * @param symbol
	 * @return String
	 */
	public static String getInnerString(String searchFrom, char symbol) {
		Matcher matcher = Pattern.compile(symbol + ".*" + symbol).matcher(searchFrom);
		String result = null;
		if (matcher.find()) {
			result = matcher.group();
			result = result.substring(1, result.length() - 1);
		}
		return result;
	}
}
