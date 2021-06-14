package calculator;

public class GroupOperator implements Calculatable {
	boolean result;

	static Calculatable of(Calculatable calculatable, boolean not) {
		GroupOperator operator = new GroupOperator();
		operator.result = not != calculatable.calculate();
		return operator;
	}

	@Override
	public boolean calculate() {
		return result;
	}
}
