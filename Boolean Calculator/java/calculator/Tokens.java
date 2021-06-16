package calculator;

import java.util.List;

public class Tokens {
	private final List<Token> tokens;

	Tokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	Token first() {
		return tokens.get(0);
	}

	boolean isLast() {
		return tokens.size() == 1;
	}

	boolean isEmpty() {
		return tokens.isEmpty();
	}

	Tokens copyRemoveFirst() {
		return new Tokens(tokens.subList(1, tokens.size()));
	}

	Tokens copyFrom(int startIndex, int endIndex) {
		return new Tokens(tokens.subList(startIndex, endIndex));
	}

	Tokens copyFrom(int startIndex) {
		return new Tokens(tokens.subList(startIndex, tokens.size()));
	}

	int getMatchingClosingBracket() {
		int closePos = 0;
		int counter = 1;
		while (counter > 0) {
			Token token = tokens.get(++closePos);
			if (token == Token.BRACKET_OPEN) {
				counter++;
			} else if (token == Token.BRACKET_CLOSE) {
				counter--;
			}
		}
		return closePos;
	}
}
