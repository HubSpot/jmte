package com.floreysoft.jmte.token;

import java.util.List;

public class ElseIfToken extends IfToken {

	public static final String ELSE_IF = "elseif";
	
	protected ElseIfToken elseIfToken = null;
	
	public ElseIfToken(List<String> segments, String expression, boolean negated) {
		super(segments, expression, negated);
	}
	
	public ElseIfToken(String ifExpression, boolean negated) {
		super(ifExpression, negated);
	}

	@Override
	public String getText() {
		if (text == null) {
			text = ELSE_IF + " " + getExpression();
		}
		return text;
	}
	
	@Override
	public String emit() {
		return ELSE_IF;
	}

}
