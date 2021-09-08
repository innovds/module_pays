package org.innovds.data.service.mock;

public class IdGenerator {

	private static long GENERATED_ID = 0l;
	
	public static Long nextId() {
		return ++GENERATED_ID;
	}
}
