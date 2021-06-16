package calculator;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BooleanCalculator {
	public boolean calculate(String input) {
		return aa(input).calculate();
	}

	Calculatable aa(String input) {
		input = input.strip();
		if (input.equalsIgnoreCase("true")) {
			return new True();
		}
		if (input.equalsIgnoreCase("false")) {
			return new False();
		}

		Optional<Calculatable> notCalculatable = checkNotCalculatable(input);
		if (notCalculatable.isPresent()) {
			return notCalculatable.get();
		}

		String left;
		if (input.startsWith("(")) {
			int position = getEndingParentecis(input);
			left = input.substring(1, position);
			input = input.substring(position + 1).trim();
		} else {
			String timeRegex = "(TRUE|FALSE)\s(.+)";
			Pattern pattern = Pattern.compile(timeRegex);
			Matcher matcher = pattern.matcher(input);
			if (matcher.matches()) {
				left = matcher.group(1);
				input = input.substring(left.length() + 1);
			} else {
				throw new IllegalStateException("sd");
			}
		}
		if (input.isEmpty()) {
			return GroupOperator.of(aa(left), false);
		}

		String timeRegex = "(OR|AND)\s(.+)";
		Pattern pattern = Pattern.compile(timeRegex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.matches()) {
			String operator = matcher.group(1);
			String right = matcher.group(2);
			return operatorOfString(operator).of(aa(left), aa(right));
		} else {
			throw new IllegalStateException("sd");
		}
	}

	private int getEndingParentecis(String input) {
		int closePos = 0;
		int counter = 1;
		while (counter > 0) {
			char c = input.charAt(++closePos);
			if (c == '(') {
				counter++;
			} else if (c == ')') {
				counter--;
			}
		}
		return closePos;
	}

	private Optional<Calculatable> checkNotCalculatable(String input) {
		String timeRegex1 = "NOT\s(.+)";
		Pattern pattern1 = Pattern.compile(timeRegex1);
		Matcher matcher1 = pattern1.matcher(input);
		if (matcher1.matches()) {
			return Optional.of(GroupOperator.of(aa(matcher1.group(1)), true));
		}
		return Optional.empty();
	}

	private CombineOperator operatorOfString(String operator) {
		if (operator.equalsIgnoreCase("and")) {
			return AndOperator::of;
		}
		return OrOperator::of;
	}


}
