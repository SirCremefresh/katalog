import calculator.BooleanCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BooleanCalculatorShould {

	@ParameterizedTest
	@ValueSource(strings = {
			"TRUE",
			"NOT FALSE",
			"TRUE AND TRUE"
	})
	void shouldBeTrueOn(String input) {
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertTrue(outcome);
	}

	@ParameterizedTest
	@ValueSource(strings = {
			"FALSE",
			"NOT TRUE"
	})
	void shouldBeFalseOn(String input) {
		BooleanCalculator booleanCalculator = new BooleanCalculator();

		boolean outcome = booleanCalculator.calculate(input);

		Assertions.assertFalse(outcome);
	}

}
