package calculator;

class AndOperator implements Calculable {
	Calculable val1;
	Calculable val2;

	private AndOperator() {
	}

	public static Calculable of(Calculable val1, Calculable val2) {
		var orOperator = new AndOperator();
		orOperator.val1 = val1;
		orOperator.val2 = val2;
		return orOperator;
	}

	public boolean calculate() {
		return val1.calculate() && val2.calculate();
	}
}
