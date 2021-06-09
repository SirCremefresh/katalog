import calculator.BooleanCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BooleanCalculatorShould {

	@Test
	void shouldReturnTrueOnTRUE() {
		String input = "TRUE";
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertTrue(outcome);
	}

	@Test
	void shouldReturnFalseOnFALSE() {
		String input = "FALSE";
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertFalse(outcome);
	}

	@Test
	void shouldReturnFalseOnNOT_TRUE() {
		String input = "NOT TRUE";
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertFalse(outcome);
	}

	@Test
	void shouldReturnTrueOnNOT_FALSE() {
		String input = "NOT FALSE";
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertTrue(outcome);
	}

}
