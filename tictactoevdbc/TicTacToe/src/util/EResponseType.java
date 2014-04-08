package util;

import java.io.Serializable;

public enum EResponseType implements Serializable{
	THIS_IS_LIST_OF_CLIENTS("this is list of clients");

	private final String name;

	private EResponseType(final String query) {
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
