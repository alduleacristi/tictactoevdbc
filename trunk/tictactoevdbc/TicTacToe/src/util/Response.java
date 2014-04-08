package util;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -980121447706239156L;
	private EResponseType responseType;

	public EResponseType getResponseType() {
		return responseType;
	}

	public void setResponseType(EResponseType responseType) {
		this.responseType = responseType;
	}
	
	
}
