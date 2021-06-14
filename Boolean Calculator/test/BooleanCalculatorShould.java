import calculator.BooleanCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BooleanCalculatorShould {

	@ParameterizedTest
	@ValueSource(strings = {
			"TRUE",
			"NOT FALSE",
			"TRUE AND TRUE",
			"TRUE OR TRUE",
			"TRUE OR TRUE OR TRUE AND FALSE",
			"TRUE OR FALSE AND NOT FALSE",
			"((TRUE OR TRUE) OR (TRUE AND FALSE))",
	})
	void shouldBeTrueOn(String input) {
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertTrue(outcome);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"FALSE",
			"NOT TRUE",
			"TRUE AND FALSE",
			"FALSE OR FALSE",
			"(TRUE OR TRUE OR TRUE) AND FALSE",
			"NOT ((TRUE OR TRUE) OR (TRUE AND FALSE))",
			"NOT (TRUE AND TRUE)",
	})
	void shouldBeFalseOn(String input) {
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertFalse(outcome);
	}

}
