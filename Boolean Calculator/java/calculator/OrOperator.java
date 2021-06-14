package calculator;

public class OrOperator implements Calculatable {
	private boolean result;

	private OrOperator() {
	}

	public boolean calculate() {
		return result;
	}

	public static Calculatable of(Calculatable val1, Calculatable val2) {
		var orOperator = new OrOperator();
		orOperator.result = val1.calculate() || val2.calculate();
		return orOperator;
	}
}
