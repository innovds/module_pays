package org.innovds.util;

import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ReflectUtils {
	
	public static Class<?> findParameterType(Object instance, int parameterIndex) {
		try {
			return findSubClassParameterType(instance, parameterIndex);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static Class<?> findSubClassParameterType(Object instance/*, Class<?> classOfInterest*/, int parameterIndex) {
		Map<Type, Type> typeMap = new HashMap<Type, Type>();
		Class<?> instanceClass = instance.getClass();
//		while (classOfInterest != instanceClass.getSuperclass()) {
////		    extractTypeArguments(typeMap, instanceClass);
//			instanceClass = instanceClass.getSuperclass();
//			if (instanceClass == null)
//				throw new IllegalArgumentException();
//		}

		ParameterizedType parameterizedType = (ParameterizedType) instanceClass.getGenericSuperclass();
		Type actualType = parameterizedType.getActualTypeArguments()[parameterIndex];
		if (typeMap.containsKey(actualType)) {
			actualType = typeMap.get(actualType);
		}

		if (actualType instanceof Class) {
			return (Class<?>) actualType;
//		} else if (actualType instanceof TypeVariable) {
//			return browseNestedTypes(instance, (TypeVariable<?>) actualType);
//		} else {
//			throw new IllegalArgumentException();
		}
		return null;
	}

//	private static Class<?> browseNestedTypes(Object instance, TypeVariable<?> actualType) {
//		Class<?> instanceClass = instance.getClass();
//		List<Class<?>> nestedOuterTypes = new LinkedList<Class<?>>();
//		for (Class<?> enclosingClass = instanceClass
//				.getEnclosingClass(); enclosingClass != null; enclosingClass = enclosingClass.getEnclosingClass()) {
//			try {
//				Field this$0 = instanceClass.getDeclaredField("this$0");
//				Object outerInstance = this$0.get(instance);
//				Class<?> outerClass = outerInstance.getClass();
//				nestedOuterTypes.add(outerClass);
//				Map<Type, Type> outerTypeMap = new HashMap<Type, Type>();
////		      extractTypeArguments(outerTypeMap, outerClass);
//				for (Map.Entry<Type, Type> entry : outerTypeMap.entrySet()) {
//					if (!(entry.getKey() instanceof TypeVariable)) {
//						continue;
//					}
//					TypeVariable<?> foundType = (TypeVariable<?>) entry.getKey();
//					if (foundType.getName().equals(actualType.getName())
//							&& isInnerClass(foundType.getGenericDeclaration(), actualType.getGenericDeclaration())) {
//						if (entry.getValue() instanceof Class) {
//							return (Class<?>) entry.getValue();
//						}
//						actualType = (TypeVariable<?>) entry.getValue();
//					}
//				}
//			} catch (NoSuchFieldException e) {
//				/* this should never happen */ } catch (IllegalAccessException e) {
//				/* this might happen */}
//
//		}
//		throw new IllegalArgumentException();
//	}
//
//	private static boolean isInnerClass(GenericDeclaration outerDeclaration, GenericDeclaration innerDeclaration) {
//		if (!(outerDeclaration instanceof Class) || !(innerDeclaration instanceof Class)) {
//			throw new IllegalArgumentException();
//		}
//		Class<?> outerClass = (Class<?>) outerDeclaration;
//		Class<?> innerClass = (Class<?>) innerDeclaration;
//		while ((innerClass = innerClass.getEnclosingClass()) != null) {
//			if (innerClass == outerClass) {
//				return true;
//			}
//		}
//		return false;
//	}

}
