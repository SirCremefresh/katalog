package stringcalculator;

import java.util.Arrays;

public class StringCalculator {
	public int calculate(String input) {
		return Arrays.stream(input
				.split(","))
				.filter(value -> !value.isEmpty())
				.mapToInt(Integer::parseInt)
				.sum();
	}
}
