package util;

import java.io.Serializable;

public enum ERequestType implements Serializable{
	THIS_IS_MY_NAME("this is my name");

	private final String name;

	private ERequestType(final String query) {
		this.name = query;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
