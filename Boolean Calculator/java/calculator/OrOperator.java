package calculator;

public class OrOperator {
	private boolean result;

	private OrOperator() {
	}

	static OrOperator of(boolean val1, boolean val2) {
		var orOperator = new OrOperator();
		orOperator.result = val1 && val2;
		return orOperator;
	}

	public boolean calculate() {
		return result;
	}
}
