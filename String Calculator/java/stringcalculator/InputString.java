package stringcalculator;

import java.util.LinkedList;
import java.util.List;

public class InputString {
	private final String input;

	InputString(String input) {
		this.input = input;
	}

	boolean hasAdditionalDelimiter() {
		return input.startsWith("//");
	}

	boolean hasMultipleAdditionalDelimiters() {
		return input.startsWith("//[");
	}

	String getValue() {
		if (hasMultipleAdditionalDelimiters()) {
			return input.substring(input.lastIndexOf("]") + 1);
		}
		if (hasAdditionalDelimiter()) {
			return input.substring(4);
		}
		return input;
	}

	List<String> getDelimiters() {
		if (hasMultipleAdditionalDelimiters()) {
			return parseMultipleDelimiters();
		}
		if (hasAdditionalDelimiter()) {
			return List.of(input.substring(2, 3));
		}
		return List.of();
	}

	private List<String> parseMultipleDelimiters() {
		List<String> delimiters = new LinkedList<>();
		String delimitersString = input.substring(input.indexOf("["), input.lastIndexOf("]") + 1);
		StringBuilder delimiter = new StringBuilder();
		for (var character : delimitersString.split("")) {
			if ("[".equals(character)) {
				continue;
			}
			if ("]".equals(character)) {
				delimiters.add(delimiter.toString());
				delimiter = new StringBuilder();
				continue;
			}
			delimiter.append(character);
		}
		return delimiters;
	}
}