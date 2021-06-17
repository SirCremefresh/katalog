package stringcalculator;

import java.util.List;
import java.util.stream.Collectors;

public class StringCalculator {
	public int calculate(String input) {
		ParsedString parsedString = ParsedString.of(input);

		var numbers = parsedString
				.parseIntegers()
				.stream()
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
