package com.laptop.bag.exception;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String errorMessage;

	@XmlElement(name = "ErrorMessage")
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@XmlElement(name = "ErrorCode")
	public Status getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Status errorCode) {
		this.errorCode = errorCode;
	}

	private Status errorCode;
	
	public ErrorMessage() {
	}
	
	public ErrorMessage(Status statusCode, String errorMessge) {
		this.errorCode = statusCode;
		this.errorMessage = errorMessge;
	}
}
