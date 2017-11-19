package br.com.embraer.flights.model.entities.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	/**
	 * Transform String formattedDateTime into LocalDateTime dateTime.
	 * @param formattedDateTime - yyyy-MM-dd HH:mm
	 * @return dateTime
	 */
	public static LocalDateTime toLocalDateTime(String formattedDateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(formattedDateTime, formatter);
		return dateTime;
	}

	/**
	 * Transform LocalDateTime dateTime into String formattedDateTime.
	 * @param dateTime
	 * @return formattedDateTime - yyyy-MM-dd HH:mm
	 */
	public static String toFormattedDateTime(LocalDateTime dateTime){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		String formattedDateTime = dateTime.format(formatter);
		return formattedDateTime;
	}


}
