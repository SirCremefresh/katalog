package calculator;

public class OrOperator implements Calculatable {
	private boolean result;
	Calculatable val1;
	Calculatable val2;
	private OrOperator() {
	}

	public boolean calculate() {
		return val1.calculate() || val2.calculate();
	}

	public static Calculatable of(Calculatable val1, Calculatable val2) {
		var orOperator = new OrOperator();
		orOperator.val1 = val1;
		orOperator.val2 = val2;
		return orOperator;
	}
}
