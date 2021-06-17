package stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalculatorShould {

	@Test
	void returnZeroOnEmptyString() {
		String input = "";
		StringCalculator calculator = new StringCalculator();

		int output = calculator.calculate(input);

		Assertions.assertEquals(0, output);
	}

	@Test
	void returnOneOnOneString() {
		String input = "1";
		StringCalculator calculator = new StringCalculator();

		int output = calculator.calculate(input);

		Assertions.assertEquals(1, output);
	}

}
