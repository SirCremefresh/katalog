package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
	public int calculate(String input) {
		String delimiters = ",\n";
		if (input.startsWith("//[")) {
			int closingBracket = input.indexOf("]");
			delimiters += input.substring(3, closingBracket).replace("\\", "\\\\");
			input = input.substring(closingBracket + 1);
		} else if (input.startsWith("//")) {
			delimiters += input.substring(2, 3);
			input = input.substring(3);
		}

		var numbers = Arrays.stream(input
				.split("[" + delimiters + "]"))
				.filter(value -> !value.isEmpty())
				.map(Integer::parseInt)
				.filter(integer -> integer <= 1000)
				.collect(Collectors.toList());

		assertNoNegativeNumbers(numbers);

		return numbers
				.stream()
				.mapToInt(Integer::intValue)
				.sum();
	}

	private void assertNoNegativeNumbers(List<Integer> numbers) {
		List<Integer> negativeNumbers = numbers
				.stream()
				.filter(number -> number < 0)
				.collect(Collectors.toList());

		if (!negativeNumbers.isEmpty()) {
			throw new RuntimeException(negativeNumbers.toString());
		}
	}
}
