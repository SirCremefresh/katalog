package calculator;

import java.util.Map;

public enum Token {
	AND, OR, BRACKET_OPEN, BRACKET_CLOSE, TRUE, FALSE, NOT;

	private static Map<String, Token> stringTokenMap = Map.of(
			"AND", Token.AND,
			"OR", Token.OR,
			"(", Token.BRACKET_OPEN,
			")", Token.BRACKET_CLOSE,
			"TRUE", Token.TRUE,
			"FALSE", Token.FALSE,
			"NOT", Token.NOT
	);

	static Token of(String tokenString) {
		var token = stringTokenMap.get(tokenString);
		if (token == null) {
			throw new IllegalStateException("Could not parse token string to token. tokenString: " + tokenString);
		}
		return token;
	}
}
