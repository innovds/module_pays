package org.innovds.data.repository;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(readOnly = true)
public abstract class AbstractJpaRepository<T, ID> implements IGenericJpaRepository<T, ID> {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	private EntityManager em;
	private Class<T> domainClass;
	private JpaRepository<T, ID> repository;

	@Override
	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
		repository = new SimpleJpaRepository<T, ID>(domainClass, em);
	}

	public Class<T> getDomainClass() {
		return domainClass;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
		log.trace("inject entityManager");
	}

	@Override
	@Transactional
	public <S extends T> S save(S entity) {
//		findBy("LastNAa")
		return repository.save(entity);
	}

	@Override
	@Transactional
	public <S extends T> S saveAndFlush(S entity) {
		return repository.saveAndFlush(entity);
	}

	@Override
	@Transactional
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}

	@Override
	@Transactional
	public void deleteById(ID id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		repository.delete(entity);
	}

	@Override
	@Transactional
	public void deleteAll(Iterable<? extends T> entities) {
		repository.deleteAll(entities);
	}

	@Override
	@Transactional
	public void deleteAll() {
		repository.deleteAll();
	}

	@Override
	@Transactional
	public void deleteInBatch(Iterable<T> entities) {
		repository.deleteInBatch(entities);
	}

	@Override
	@Transactional
	public void deleteAllInBatch() {
		repository.deleteAllInBatch();
	}

	@Override
	@Transactional
	public void flush() {
		repository.flush();
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T getOne(ID id) {
		return repository.getOne(id);
	}

	@Override
	public <S extends T> Optional<S> findOne(Example<S> example) {
		return repository.findOne(example);
	}

	@Override
	public List<T> findAllById(Iterable<ID> ids) {
		return repository.findAllById(ids);
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<?> find(String criteria) {
		return repository.findAll();
	}

	@Override
	public Page<?> find(String criteria, Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		return repository.findAll(example);
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repository.findAll(example, pageable);
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		return repository.findAll(example, sort);
	}

	@Override
	public boolean existsById(ID id) {
		return repository.existsById(id);
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		return repository.exists(example);
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		return repository.count(example);
	}

	@Override
	public long count() {
		return repository.count();
	}
	
	@Override
	public <DTO> TypedQuery<DTO> createQuery(String query, Class<DTO> dtoClass) {
		return em.createQuery(query, dtoClass);
	}


	private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,
			CriteriaQuery<S> query) {

		Assert.notNull(domainClass, "Domain class must not be null!");
		Assert.notNull(query, "CriteriaQuery must not be null!");

		Root<U> root = query.from(domainClass);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}

//	protected QueryHints getQueryHints() {
//		return metadata == null ? NoHints.INSTANCE : DefaultQueryHints.of(entityInformation, metadata);
//	}
//
//	private void applyQueryHints(Query query) {
//		getQueryHints().withFetchGraphs(em).forEach(query::setHint);
//	}

//	private <S> TypedQuery<S> applyRepositoryMethodMetadata(TypedQuery<S> query) {
//
//		if (metadata == null) {
//			return query;
//		}
//
//		LockModeType type = metadata.getLockModeType();
//		TypedQuery<S> toReturn = type == null ? query : query.setLockMode(type);
//
//		applyQueryHints(toReturn);
//
//		return toReturn;
//	}

	protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass, Sort sort) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<S> query = builder.createQuery(domainClass);

		Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
		query.select(root);

		if (sort.isSorted()) {
			query.orderBy(toOrders(sort, root, builder));
		}

		return em.createQuery(query);
//		return applyRepositoryMethodMetadata(em.createQuery(query));
	}


}