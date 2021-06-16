package calculator;

class True implements Calculable {

	static Calculable of() {
		return new True();
	}

	@Override
	public boolean calculate() {
		return true;
	}
}
