package br.com.embraer.flights.business.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.embraer.flights.business.service.IDataService;

public abstract class AbstractDataService<T, ID extends Serializable> implements IDataService<T, ID> {
	@Override
	public List<T> findAll() {
		return getRepository().findAll();
	}

	@Override
	public List<T> findAll(Sort sort) {
		return getRepository().findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return getRepository().findAll(pageable);
	}

	@Override
	public T findById(ID id) {
		return getRepository().findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return getRepository().exists(id);
	}

	@Override
	public Long count() {
		return getRepository().count();
	}

	@Override
	@Transactional
	public T save(T entity) {
		return getRepository().save(entity);
	}

	@Override
	@Transactional
	public T saveAndFlush(T entity) {
		return getRepository().saveAndFlush(entity);
	}

	@Override
	@Transactional
	public List<T> save(Iterable<T> entities) {
		return getRepository().save(entities);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		getRepository().delete(entity);
	}

	@Override
	@Transactional
	public void delete(Iterable<? extends T> entities) {
		getRepository().delete(entities);
	}

	@Override
	@Transactional
	public void deleteAll() {
		getRepository().deleteAll();
	}

	@Override
	@Transactional
	public void deleteInBatch(Iterable<T> entities) {
		getRepository().deleteInBatch(entities);
	}

	public abstract JpaRepository<T, ID> getRepository();

}
