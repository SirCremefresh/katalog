package calculator;

public class GroupOperator implements Calculatable {
	Calculatable calculatable;

	static Calculatable of(Calculatable calculatable) {
		GroupOperator operator = new GroupOperator();
		operator.calculatable = calculatable;
		return operator;
	}

	@Override
	public boolean calculate() {
		return calculatable.calculate();
	}
}
