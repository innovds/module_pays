package org.innovds.data.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IGenericService<T, ID> {

	<DTO> Optional<DTO> findOne(ID id);

	<DTO> Page<DTO> find(String filter, Pageable pageable);

	<DTO> List<DTO> find(String filter);

	long count(String filter);

	T save(T entity);

	T update(T entity);

	Void delete(ID id);

	default void setDomainClass(Class<T> domainClass) {}
}