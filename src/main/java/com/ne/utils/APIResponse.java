package com.ne.utils;
public class APIResponse {
	
	private EStatus status;
	private Object data;
	private String message;
	
	public APIResponse() {
		super();
	}

	public APIResponse(EStatus status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public APIResponse(EStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public APIResponse(EStatus status, Object data, String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public EStatus getStatus() {
		return status;
	}

	public void setStatus(EStatus status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}