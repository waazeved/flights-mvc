package br.com.embraer.flights.business.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IDataService <T, ID extends Serializable>{
	List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    T findById(ID id);

    boolean exists(ID id);

    Long count();

    T save(T entity);

    T saveAndFlush(T entity);

    List<T> save(Iterable<T> entities);

    void delete(T entity);

    void delete(Iterable<? extends T> entities);

    void deleteAll();

    void deleteInBatch(Iterable<T> entities);

    JpaRepository<T, ID> getRepository();
}
