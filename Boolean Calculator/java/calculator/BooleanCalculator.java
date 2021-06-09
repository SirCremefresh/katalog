package calculator;

public class BooleanCalculator {
	public boolean calculate(String input) {
		String[] inputSplit = input.split("\s");
		String lastString = inputSplit[inputSplit.length - 1];
		boolean hasMoreThanOne = inputSplit.length > 1;
		return !hasMoreThanOne == Boolean.parseBoolean(lastString);
	}
}
