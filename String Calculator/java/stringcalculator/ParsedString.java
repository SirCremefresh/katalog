package stringcalculator;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ParsedString {
	private static final List<String> DEFAULT_DELIMITERS = List.of(",", "\n");

	private final List<String> delimiters = new LinkedList<>();
	private final String value;

	private ParsedString(List<String> additionalDelimiters, String value) {
		this.delimiters.addAll(DEFAULT_DELIMITERS);
		this.delimiters.addAll(additionalDelimiters);
		this.value = value;
	}

	static ParsedString of(String input) {
		List<String> additionalDelimiters = new LinkedList<>();
		if (input.startsWith("//[")) {
			int closingBracket = input.indexOf("]");
			additionalDelimiters.add(input.substring(3, closingBracket).replace("\\", "\\\\"));
			input = input.substring(closingBracket + 1);
		} else if (input.startsWith("//")) {
			additionalDelimiters.add(input.substring(2, 3));
			input = input.substring(3);
		}
		return new ParsedString(additionalDelimiters, input);
	}

	String getValue() {
		return value;
	}

	String getDelimiterRegex() {
		var delimitersString =  delimiters.stream()
				.map(delimiter -> delimiter.replace("\\", "\\\\"))
				.collect(Collectors.joining());
		return "[" + delimitersString + "]";
	}
}
