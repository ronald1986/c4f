package com.biztrace.dao.model;

public class Criteria {
	public static enum OPERATOR {EQ, NEQ};
	
	private String propertyName;
	private OPERATOR operator;
	private Object propertyValue;
	
	public Criteria(final String propertyName,
			final Object propertyValue) {
		this.propertyName = propertyName;
		this.operator = OPERATOR.EQ;
		this.propertyValue = propertyValue;
	}

	public Criteria(final String propertyName, final Criteria.OPERATOR operator,
			final Object propertyValue) {
		this.propertyName = propertyName;
		this.operator = operator;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public OPERATOR getOperator() {
		return operator;
	}

	public void setOperator(OPERATOR operator) {
		this.operator = operator;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}
	
}
