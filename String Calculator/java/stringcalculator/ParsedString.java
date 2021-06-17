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

	static ParsedString of(String input) {
		var inputString = new InputString(input);
		return new ParsedString(inputString.getDelimiters(), inputString.getValue());
	}

	List<Integer> parseIntegers() {
		return Arrays.stream(value
				.split(getDelimiterRegex()))
				.filter(value -> !value.isEmpty())
				.map(Integer::parseInt)
				.collect(Collectors.toList());
	}

	private String getDelimiterRegex() {
		return delimiters.stream()
				.map(delimiter -> delimiter.replaceAll("[\\W]", "\\\\$0"))
				.collect(Collectors.joining("|"));
	}
}
