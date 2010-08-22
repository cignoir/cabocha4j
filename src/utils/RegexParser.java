package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author noire722
 *
 */
public class RegexParser {
	public static final String DOUBLE_QUATE = "\"";
	
	/**
	 * Find and get the inner string between the specified symbol in
	 * constructor's argument.If not found, return null.
	 * 
	 * @param symbol
	 * @return String
	 */
	public static String getInnerString(String searchFrom, String start, String end) {
		Matcher matcher = Pattern.compile(start + ".*" + end).matcher(searchFrom);
		String result = null;
		if (matcher.find()) {
			result = matcher.group();
			result = result.replaceFirst(start, "");
			result = result.replaceFirst(end, "");
		}
		return result;
	}
}
