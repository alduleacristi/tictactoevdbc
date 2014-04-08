package util;

import java.io.Serializable;

public class SendName implements Serializable {
	
	private static final long serialVersionUID = 7370175875243908240L;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
