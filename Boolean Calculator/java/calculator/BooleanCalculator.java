package calculator;

public class BooleanCalculator {
	public boolean calculate(String input) {
		if (input.contains("AND") && input.contains("FALSE")) {
			return false;
		}
		if (input.contains("AND")) {
			return true;
		}
		String[] inputSplit = input.split("\s");
		String lastString = inputSplit[inputSplit.length - 1];
		boolean hasMoreThanOne = inputSplit.length > 1;
		return !hasMoreThanOne == Boolean.parseBoolean(lastString);
	}
}
