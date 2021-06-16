package calculator;

class AndOperator implements Calculatable {
	Calculatable val1;
	Calculatable val2;

	private AndOperator() {
	}

	public static Calculatable of(Calculatable val1, Calculatable val2) {
		var orOperator = new AndOperator();
		orOperator.val1 = val1;
		orOperator.val2 = val2;
		return orOperator;
	}

	public boolean calculate() {
		return val1.calculate() && val2.calculate();
	}
}
