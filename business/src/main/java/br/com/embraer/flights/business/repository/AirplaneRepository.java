
package br.com.embraer.flights.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.embraer.flights.model.entities.Airplane;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {

}
