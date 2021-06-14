package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanCalculator {
	public boolean calculate(String input) {
		return aa(input).calculate();
	}

	Calculatable aa(String input) {
		if (input.equalsIgnoreCase("true")) {
			return new TrueOperator();
		}
		if (input.equalsIgnoreCase("false")) {
			return new FalseOperator();
		}

		String timeRegex1 = "NOT\s(.+)";
		Pattern pattern1 = Pattern.compile(timeRegex1);
		Matcher matcher1 = pattern1.matcher(input);
		if (matcher1.matches()) {
			return GroupOperator.of(aa(matcher1.group(1)), true);
		}

		String timeRegex = "(TRUE|FALSE)\s(OR|AND)\s(.+)";
		Pattern pattern = Pattern.compile(timeRegex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			String left = matcher.group(1);
			String operator = matcher.group(2);
			String right = matcher.group(3);
			return operatorOfString(operator).of(aa(left), aa(right));
		} else {
			throw new IllegalStateException("sd");
		}
	}

	private CombineOperator operatorOfString(String operator) {
		if (operator.equalsIgnoreCase("and")) {
			return AndOperator::of;
		}
		return OrOperator::of;
	}


}
