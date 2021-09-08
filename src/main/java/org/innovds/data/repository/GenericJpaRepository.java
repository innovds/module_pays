package org.innovds.data.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class GenericJpaRepository<T, ID> extends AbstractJpaRepository<T, ID> {

//	@PostConstruct
//	public void init() {
//		log.trace("==> init " + this.getClass().getName());
//	}

}
