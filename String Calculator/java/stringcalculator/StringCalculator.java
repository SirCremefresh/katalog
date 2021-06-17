package stringcalculator;

import java.util.Arrays;

public class StringCalculator {
	public int calculate(String input) {
		String delimiters = ",\n";
		if (input.startsWith("//")) {
			delimiters += input.substring(2, 3);
			input = input.substring(3);
		}

		return Arrays.stream(input
				.split("[" + delimiters + "]{1,2}"))
				.filter(value -> !value.isEmpty())
				.mapToInt(Integer::parseInt)
				.sum();
	}
}
