package br.com.embraer.flights.business.exception;


public class NameIsAlreadyInUseException extends Exception {


	private static final long serialVersionUID = 1L;


	public NameIsAlreadyInUseException(String message) {
		super(message);
	}
}
