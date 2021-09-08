package org.innovds.data.web;

import org.innovds.data.service.IGenericService;
import org.innovds.util.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractController<T, ID> {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	protected IGenericService<T, ID> service;

//	@PostConstruct
//	public void init() {
//		log.trace("====> init " + this.getClass().getName());
//	}

	@SuppressWarnings("unchecked")
	@Autowired
	public void setService(IGenericService<T, ID> service) {
		this.service = service;
		Class<?> domainClass = ReflectUtils.findParameterType(this, 0);
		if (domainClass != null) {
			service.setDomainClass((Class<T>) domainClass);
			log.trace(this.getClass().getName() + " : " + domainClass);
		}
	}

}