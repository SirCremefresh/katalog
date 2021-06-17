package stringcalculator;

import java.util.Arrays;
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

	static ParsedString of(InputString inputString) {
		List<String> additionalDelimiters = new LinkedList<>();
		if (inputString.hasAdditionalDelimiter()) {
			additionalDelimiters = inputString.getDelimiters();
		}

		return new ParsedString(additionalDelimiters, inputString.getValue());
	}

	private String getValue() {
		return value;
	}

	private String getDelimiterRegex() {
		var delimitersString = delimiters.stream()
				.map(delimiter -> delimiter.replace("\\", "\\\\"))
				.collect(Collectors.joining());
		return "[" + delimitersString + "]";
	}

	List<Integer> parseIntegers() {
		return Arrays.stream(getValue()
				.split(getDelimiterRegex()))
				.filter(value -> !value.isEmpty())
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}
}
