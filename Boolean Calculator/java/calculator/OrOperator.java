package calculator;

public class OrOperator implements Calculable {
	private boolean result;
	Calculable val1;
	Calculable val2;
	private OrOperator() {
	}

	public boolean calculate() {
		return val1.calculate() || val2.calculate();
	}

	public static Calculable of(Calculable val1, Calculable val2) {
		var orOperator = new OrOperator();
		orOperator.val1 = val1;
		orOperator.val2 = val2;
		return orOperator;
	}
}
