package com.floreysoft.jmte.token;

import java.util.List;

import com.floreysoft.jmte.TemplateContext;

public class ElseIfCmpToken extends ElseIfToken {
	private final String operand;

	public ElseIfCmpToken(String expression, String operand, boolean negated) {
		super(expression, negated);
		this.operand = operand;
	}

	public ElseIfCmpToken(List<String> segments, String expression, String operand, boolean negated) {
		super(segments, expression, negated);
		this.operand = operand;
	}

	public String getOperand() {
		return operand;
	}

	@Override
	public String getText() {
		if (text == null) {
			text = String
					.format(ELSE_IF + " %s='%s'", getExpression(), getOperand());
		}
		return text;
	}

	@Override
	public Object evaluate(TemplateContext context) {
		final Object value = evaluatePlain(context);
		final boolean condition = getOperand().equals(value.toString());
		final Object evaluated = negated ? !condition : condition;
		return evaluated;
	}
}
