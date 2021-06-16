package calculator;

public class GroupOperator implements Calculable {
	Calculable calculable;

	static Calculable of(Calculable calculable) {
		GroupOperator operator = new GroupOperator();
		operator.calculable = calculable;
		return operator;
	}

	@Override
	public boolean calculate() {
		return calculable.calculate();
	}
}
