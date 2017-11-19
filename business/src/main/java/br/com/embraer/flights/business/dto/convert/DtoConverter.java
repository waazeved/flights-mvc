package br.com.embraer.flights.business.dto.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class DtoConverter<E, D> {

	public List<D> toDtoList(Iterable<E> entityList, final ToDtoFunction<E, D> function) {
		List<D> dtoList = new ArrayList<D>();
		for (Iterator<E> iterator = entityList.iterator(); iterator.hasNext();) {
			E entity = (E) iterator.next();
			D dto = function.call(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}

	public List<E> toEntityList(Iterable<D> dtoList, final ToEntityFunction<E, D> function) {
		List<E> entityList = new ArrayList<E>();
		for (Iterator<D> iterator = dtoList.iterator(); iterator.hasNext();) {
			D dto = (D) iterator.next();
			E entity = function.call(dto);
			entityList.add(entity);
		}
		return entityList;
	}

	@FunctionalInterface
	protected interface ToDtoFunction<E, D> {
	    D call(E entity);
	}

	@FunctionalInterface
	protected interface ToEntityFunction<E, D> {
	    E call(D entity);
	}



}