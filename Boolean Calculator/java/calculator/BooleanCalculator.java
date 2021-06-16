package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BooleanCalculator {
	public boolean calculate(String input) {
		List<Token> tokens = getTokenStream(input);

		return buildTree(tokens).calculate();
	}

	private List<Token> getTokenStream(String input) {
		return Arrays.stream(input
				.replace("(", "( ")
				.replace(")", " )")
				.split("\s"))
				.map(Token::of)
				.collect(Collectors.toList());
	}

	Calculatable buildTree(List<Token> tokens) {
		Token first = tokens.get(0);
		if (tokens.size() == 1 && first == Token.TRUE) {
			return new True();
		}
		if (tokens.size() == 1 && first == Token.FALSE) {
			return new False();
		}
		if (first == Token.NOT) {
			return NotOperator.of(buildTree(tokens.subList(1, tokens.size())));
		}

		List<Token> left;
		if (first == Token.BRACKET_OPEN) {
			int closingBracketIndex = getMatchingClosingBracket(tokens);
			left = tokens.subList(1, closingBracketIndex);
			tokens = tokens.subList(closingBracketIndex + 1, tokens.size());
		} else {
			left = tokens.subList(0, 1);
			tokens = tokens.subList(1, tokens.size());
		}
		if (tokens.isEmpty()) {
			return GroupOperator.of(buildTree(left));
		}

		Token operator = tokens.get(0);
		return operatorOfString(operator).of(buildTree(left), buildTree(tokens.subList(1, tokens.size())));
	}

	private int getMatchingClosingBracket(List<Token> input) {
		int closePos = 0;
		int counter = 1;
		while (counter > 0) {
			Token token = input.get(++closePos);
			if (token == Token.BRACKET_OPEN) {
				counter++;
			} else if (token == Token.BRACKET_CLOSE) {
				counter--;
			}
		}
		return closePos;
	}

	private CombineOperator operatorOfString(Token operator) {
		if (operator == Token.AND) {
			return AndOperator::of;
		}
		return OrOperator::of;
	}

}
