package stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringCalculatorShould {

	@ParameterizedTest
	@CsvSource({
			"'', 0",
			"'1', 1",
			"'1,2', 3",
			"'1\n2,3', 6",
			"'1,2\n,3', 6",
			"'//;\n1\n2;3', 6",
	})
	void returnCalculatedResultForGivenInput(String input, int shouldOutput) {
		StringCalculator calculator = new StringCalculator();

		int realOutput = calculator.calculate(input);

		Assertions.assertEquals(shouldOutput, realOutput);
	}
}
