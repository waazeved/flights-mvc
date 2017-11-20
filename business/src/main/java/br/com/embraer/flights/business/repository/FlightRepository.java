package br.com.embraer.flights.business.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.embraer.flights.model.entities.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("SELECT f FROM Flight f "
			+ "WHERE ( LOWER(f.code) LIKE %:search% "
			+ "OR LOWER(f.pilot.name) LIKE %:search% "
			+ "OR LOWER(f.airplane.name) LIKE %:search% "
			+ "OR LOWER(f.status) LIKE %:search% ) "
			+ "ORDER BY f.start DESC ")
	List<Flight> findBySearchOrderByStart(@Param("search") String search, Pageable pageable);

	@Query("SELECT f FROM Flight f ORDER BY f.start DESC ")
	List<Flight> findAllOrdeByStart(Pageable pageable);

}
