package org.innovds.data.service.mock;

import java.lang.reflect.Field;

import org.innovds.data.model.Identifiable;
import org.springframework.data.util.ReflectionUtils;

//@Service("")
//@Scope("prototype")
public class MockIdLongService<T extends Identifiable<Long>> extends AbstractMockService<T, Long> {
	
	@Override
	protected void setId(T object) {
		try {
			Field idField = object.getClass().getDeclaredField("id");
			ReflectionUtils.setField(idField, object, IdGenerator.nextId());
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}

}