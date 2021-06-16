package calculator;

public class NotOperator implements Calculable {
	Calculable calculable;

	static Calculable of(Calculable calculable) {
		NotOperator operator = new NotOperator();
		operator.calculable = calculable;
		return operator;
	}

	@Override
	public boolean calculate() {
		return !calculable.calculate();
	}
}
