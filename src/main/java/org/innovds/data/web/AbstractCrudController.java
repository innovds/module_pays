package org.innovds.data.web;

import javax.validation.Valid;

import org.springframework.data.domain.Persistable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractCrudController<T extends Persistable<ID>, ID> extends AbstractReadController<T, ID> {

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public T save(@Valid @RequestBody T object) {
		return service.save(object);
	}

	@PutMapping
	public T update(@Valid @RequestBody T object) {
		return service.update(object);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public Void delete(@PathVariable ID id) {
		return service.delete(id);
	}

}
