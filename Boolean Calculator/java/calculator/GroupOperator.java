package calculator;

public class GroupOperator implements Calculatable {
	Calculatable calculatable;
	boolean not;

	static Calculatable of(Calculatable calculatable, boolean not) {
		GroupOperator operator = new GroupOperator();
		operator.calculatable = calculatable;
		operator.not = not;
		return operator;
	}

	@Override
	public boolean calculate() {
		return not != calculatable.calculate();
	}
}
