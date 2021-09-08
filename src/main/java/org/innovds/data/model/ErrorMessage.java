package org.innovds.data.model;

import java.time.LocalDateTime;

public class ErrorMessage {

	private LocalDateTime timestamp = LocalDateTime.now();
	private Integer status;
	private String error;
	private String message;
	private String path;
	private Object trace;
	
	public ErrorMessage timestamp(LocalDateTime timestamp) {
		setTimestamp(timestamp);
		return this;
	}
	public ErrorMessage status(Integer status) {
		setStatus(status);
		return this;
	}
	public ErrorMessage error(String error) {
		setError(error);
		return this;
	}
	public ErrorMessage message(String message) {
		setMessage(message);
		return this;
	}
	public ErrorMessage path(String path) {
		setPath(path);
		return this;
	}
	public ErrorMessage trace(Object trace) {
		setTrace(trace);
		return this;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Object getTrace() {
		return trace;
	}
	public void setTrace(Object trace) {
		this.trace = trace;
	}

}
