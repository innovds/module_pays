package org.innovds.data.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.innovds.data.exceptions.NotFoundException;
import org.innovds.data.model.Identifiable;
import org.innovds.data.service.IGenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

public abstract class AbstractMockService<T extends Identifiable<ID>, ID> implements IGenericService<T, ID> {

	private final List<T> data = new ArrayList<T>();

	protected abstract void setId(T object);
	
	private void simulateWait() {
		try {
			Thread.sleep((long) (Math.random() * 6000l));
		} catch (InterruptedException e) {
		}
	}
	
	public T save(T object) {
		simulateWait();
		setId(object);
		data.add(object);
		return object;
	}
	
	public T update(T object) {
		simulateWait();
		int indexOf = data.indexOf(object);
		if (indexOf != -1) {
			data.set(indexOf, object);
			return object;
		}
		throw new NotFoundException();
	}
	
	public Void delete(ID id) {
		simulateWait();
		findOne(id).map(object -> data.remove(object)).orElseThrow(NotFoundException::new);
		return null;
	}
	
	public <DTO> Optional<DTO> findOne(ID id) {
		simulateWait();
		return (Optional<DTO>) data.stream().filter(object -> ObjectUtils.nullSafeEquals(object.getId(), id)).findFirst();
	}

	public Page<T> find(String filter, Pageable pageable) {
		simulateWait();
		return new PageImpl<T>(data);
	}

	public List<T> find(String filter) {
		simulateWait();
		return data;
	}

	public long count(String filter) {
		simulateWait();
		return data.size();
	}
}
