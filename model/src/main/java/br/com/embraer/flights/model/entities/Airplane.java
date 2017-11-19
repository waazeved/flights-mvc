package br.com.embraer.flights.model.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "FLT_AIRPLANE")
public class Airplane {

	@Id
	@SequenceGenerator(name="SEQ_AIRPLANE_ID", sequenceName="SEQ_AIRPLANE_ID", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_AIRPLANE_ID")
	@Column(name = "AIRPLANE_ID")
	private long id;

	@Column(name = "AIRPLANE_NAME", unique=true)
	private String name;

	@OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY)
	private Set<Flight> flights;
}
