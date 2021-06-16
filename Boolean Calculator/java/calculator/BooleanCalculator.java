package calculator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BooleanCalculator {
	private final static Map<Token, CombineOperator> COMBINATION_OPERATORS = Map.of(
			Token.AND, AndOperator::of,
			Token.OR, OrOperator::of
	);
	private final static Map<Token, ValueOperator> VALUE_OPERATOR = Map.of(
			Token.FALSE, False::of,
			Token.TRUE, True::of
	);

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

	private Calculable buildTree(final List<Token> tokens) {
		Token first = tokens.get(0);
		if (tokens.size() == 1) {
			return getValueOperator(first).of();
		}
		if (first == Token.NOT) {
			return NotOperator.of(buildTree(tokens.subList(1, tokens.size())));
		}

		List<Token> left;
		List<Token> rest;
		if (first == Token.BRACKET_OPEN) {
			int closingBracketIndex = getMatchingClosingBracket(tokens);
			left = tokens.subList(1, closingBracketIndex);
			rest = tokens.subList(closingBracketIndex + 1, tokens.size());
		} else {
			left = tokens.subList(0, 1);
			rest = tokens.subList(1, tokens.size());
		}
		if (rest.isEmpty()) {
			return GroupOperator.of(buildTree(left));
		}

		Token operator = rest.get(0);
		return operatorOfString(operator).of(buildTree(left), buildTree(rest.subList(1, rest.size())));
	}

	private ValueOperator getValueOperator(Token token) {
		ValueOperator operator = VALUE_OPERATOR.get(token);
		if (operator == null) {
			throw new IllegalStateException("Could not get ValueOperator for Token. token: " + token);
		}
		return operator;
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

	private CombineOperator operatorOfString(Token token) {
		CombineOperator operator = COMBINATION_OPERATORS.get(token);
		if (operator == null) {
			throw new IllegalStateException("Could not get CombineOperator for Token. token: " + token);
		}
		return operator;
	}

}
