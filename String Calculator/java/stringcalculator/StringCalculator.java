package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
	public int calculate(String input) {
		String delimiters = ",\n";
		if (input.startsWith("//")) {
			delimiters += input.substring(2, 3);
			input = input.substring(3);
		}

		var numbers = Arrays.stream(input
				.split("[" + delimiters + "]{1,2}"))
				.filter(value -> !value.isEmpty())
				.map(Integer::parseInt)
				.collect(Collectors.toList());

		List<Integer> negativeNumbers = numbers
				.stream()
				.filter(number -> number < 0)
				.collect(Collectors.toList());

		if (!negativeNumbers.isEmpty()) {
			throw new RuntimeException(negativeNumbers.toString());
		}

		return numbers
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}
}
