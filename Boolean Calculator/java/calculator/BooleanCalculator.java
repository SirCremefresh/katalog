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

		return buildTree(new Tokens(tokens)).calculate();
	}

	private List<Token> getTokenStream(String input) {
		return Arrays.stream(input
				.replace("(", "( ")
				.replace(")", " )")
				.split("\s"))
				.map(Token::of)
				.collect(Collectors.toList());
	}

	private Calculable buildTree(final Tokens tokens) {
		Token first = tokens.first();
		if (tokens.isLast()) {
			return getValueOperator(first).of();
		}
		if (first == Token.NOT) {
			return NotOperator.of(buildTree(tokens.copyRemoveFirst()));
		}

		var nextBreakIndex = findNextBreakIndex(tokens);
		var startIndex = findExpressionStartIndex(tokens);
		Tokens left = tokens.copyFrom(startIndex, nextBreakIndex);
		Tokens rest = tokens.copyFrom(startIndex + nextBreakIndex);

		if (rest.isEmpty()) {
			return GroupOperator.of(buildTree(left));
		}

		CombineOperator combineOperator = getCombineOperator(rest.first());
		return combineOperator.of(
				buildTree(left),
				buildTree(rest.copyRemoveFirst())
		);
	}

	private int findNextBreakIndex(Tokens tokens) {
		if (tokens.first() == Token.BRACKET_OPEN) {
			return tokens.getMatchingClosingBracket();
		}
		return 1;
	}

	private int findExpressionStartIndex(Tokens tokens) {
		if (tokens.first() == Token.BRACKET_OPEN) {
			return 1;
		}
		return 0;
	}

	private ValueOperator getValueOperator(Token token) {
		ValueOperator operator = VALUE_OPERATOR.get(token);
		if (operator == null) {
			throw new IllegalStateException("Could not get ValueOperator for Token. token: " + token);
		}
		return operator;
	}


	private CombineOperator getCombineOperator(Token token) {
		CombineOperator operator = COMBINATION_OPERATORS.get(token);
		if (operator == null) {
			throw new IllegalStateException("Could not get CombineOperator for Token. token: " + token);
		}
		return operator;
	}

}
