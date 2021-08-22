package org.innovds.data.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGenericJpaRepository<T, ID> extends JpaRepository<T, ID> {

	void setDomainClass(Class<T> domainClass);
	
	<DTO> Page<DTO>find(String filter, Pageable page);
	<DTO> List<DTO>find(String filter);

	<DTO> TypedQuery<DTO> createQuery(String query, Class<DTO> dtoClass);

}