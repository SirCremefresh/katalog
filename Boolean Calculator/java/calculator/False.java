package calculator;

public class False implements Calculable {
	private False() {
	}

	static Calculable of() {
		return new False();
	}

	@Override
	public boolean calculate() {
		return false;
	}
}
