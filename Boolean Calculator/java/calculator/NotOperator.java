package calculator;

public class NotOperator implements Calculatable {
	Calculatable calculatable;

	static Calculatable of(Calculatable calculatable) {
		NotOperator operator = new NotOperator();
		operator.calculatable = calculatable;
		return operator;
	}

	@Override
	public boolean calculate() {
		return !calculatable.calculate();
	}
}
