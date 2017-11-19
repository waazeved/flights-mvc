package br.com.embraer.flights.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.embraer.flights.model.entities.enums.FlightStatusEnum;
import br.com.embraer.flights.model.entities.utils.DateTimeUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "FLT_FLIGHT")
public class Flight {

	@Id
	@SequenceGenerator(name = "SEQ_FLIGHT_ID", sequenceName = "SEQ_FLIGHT_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FLIGHT_ID")
	@Column(name = "FLIGHT_ID")
	private Long id;

	@Column(name = "FLIGHT_CODE")
	private String code;

	@Column(name = "FLIGHT_START")
	private LocalDateTime start;

	@Column(name = "FLIGHT_END")
	private LocalDateTime end;

	@Column(name = "FLIGHT_STATUS")
	@Enumerated(EnumType.ORDINAL)
	private FlightStatusEnum status;

	@ManyToOne
	@JoinColumn(name = "FLIGHT_PILOT_ID", referencedColumnName = "PILOT_ID")
	private Pilot pilot;

	@ManyToOne
	@JoinColumn(name = "FLIGHT_AIRPLANE_ID", referencedColumnName = "AIRPLANE_ID")
	private Airplane airplane;

	public String getStartFormattedDateTime() {
		return DateTimeUtils.toFormattedDateTime(this.start);
	}

	public String getEndFormattedDateTime() {
		return DateTimeUtils.toFormattedDateTime(this.end);
	}

}
