package util;

import java.io.Serializable;

public class Request implements Serializable {
	private static final long serialVersionUID = 1618431715894830892L;

	private ERequestType requestType;
	private SendName sendName;
	private ContactUser contactUser;
	private ResponseToPlayRequest responseToPlayRequest;
	private  Integer i, j;

	
	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}

	public Integer getJ() {
		return j;
	}

	public void setJ(Integer j) {
		this.j = j;
	}

	public ERequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(ERequestType requestType) {
		this.requestType = requestType;
	}

	public SendName getSendName() {
		return sendName;
	}

	public void setSendName(SendName sendName) {
		this.sendName = sendName;
	}

	public ContactUser getContactUser() {
		return contactUser;
	}

	public void setContactUser(ContactUser contactUser) {
		this.contactUser = contactUser;
	}

	public ResponseToPlayRequest getResponseToPlayRequest() {
		return responseToPlayRequest;
	}

	public void setResponseToPlayRequest(
			ResponseToPlayRequest responseToPlayRequest) {
		this.responseToPlayRequest = responseToPlayRequest;
	}

	@Override
	public String toString() {
		return "Request [requestType=" + requestType + ", sendName=" + sendName
				+ ", contactUser=" + contactUser + ", responseToPlayRequest="
				+ responseToPlayRequest + "]";
	}

}
