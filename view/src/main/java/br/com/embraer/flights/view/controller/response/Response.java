package br.com.embraer.flights.view.controller.response;

public class Response {

	public Boolean success;
	public Object data;
	public String exception;

	public static Response success() {

		Response response = new Response();
		response.setData(null);
		return response;
	}

	public static Response success(Object data) {

		Response response = new Response();
		response.setData(data);
		return response;
	}

	public static Response exception(Exception exception) {

		Response response = new Response();
		response.setException(exception);
		return response;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
		this.success = true;
	}

	public String getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception.getClass().getSimpleName();
		this.success = false;
	}



}
