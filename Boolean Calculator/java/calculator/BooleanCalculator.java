package calculator;

public class BooleanCalculator {
	public boolean calculate(String input) {
		String[] inputSplit = input.split("\s");
		if (input.contains("AND")) {
			return AndOperator.of(Boolean.parseBoolean(inputSplit[0]), Boolean.parseBoolean(inputSplit[2])).calculate();
		}
		if (input.contains("OR")) {
			return true;
		}
		String lastString = inputSplit[inputSplit.length - 1];
		boolean hasMoreThanOne = inputSplit.length > 1;
		return !hasMoreThanOne == Boolean.parseBoolean(lastString);
	}
}
