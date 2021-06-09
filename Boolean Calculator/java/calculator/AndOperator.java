package calculator;

class AndOperator {
	private boolean result;

	private AndOperator() {
	}

	static AndOperator of(boolean val1, boolean val2) {
		AndOperator andOperator = new AndOperator();
		andOperator.result = val1 && val2;
		return andOperator;
	}

	public boolean calculate() {
		return result;
	}
}
