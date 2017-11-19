package br.com.embraer.flights.business.exception;

public class CodeIsAlreadyInUseException extends Exception {

	private static final long serialVersionUID = 1L;



	public CodeIsAlreadyInUseException(String message) {
		super(message);
	}
}
