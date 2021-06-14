package calculator;

class AndOperator implements Calculatable {
	private boolean result;

	private AndOperator() {
	}

	static AndOperator of(Calculatable val1, Calculatable val2) {
		AndOperator andOperator = new AndOperator();
		andOperator.result = val1.calculate() && val2.calculate();
		return andOperator;
	}

	public boolean calculate() {
		return result;
	}
}
