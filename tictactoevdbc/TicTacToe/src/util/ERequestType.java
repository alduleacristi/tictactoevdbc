package util;

import java.io.Serializable;

public enum ERequestType implements Serializable{
	THIS_IS_MY_NAME("this is my name"),
	GIVE_CLIENT_LIST("give_client_list"),
	CONTACT_USER_TO_PLAY("contact_user_to_play"),
	RESPONSE_TO_PLAY_REQUEST("response_to_play_request");

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
