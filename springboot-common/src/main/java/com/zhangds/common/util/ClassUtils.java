package com.zhangds.common.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ClassUtils {
	
	private static Log logger = LogFactory.getLog(ClassUtils.class);
	
	/**
	 * 根据属性名递归获取反射字段
	 **/
	public static Field getFieldRecursion(Class<?> clazz, String fieldName){
		if(clazz == Object.class){
			return null;
		}else{
			Field field = null;
			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
			if(field == null){
				return getFieldRecursion(clazz.getSuperclass(), fieldName);
			}
			return field;
		}
	}

	/**
	 * 根据反射字段获取值
	 **/
	public static Object getValueByField(Object obj, Field field){
		field.setAccessible(true);
		try {
			return field.get(obj);
		} catch (IllegalAccessException e) {
			e.getStackTrace();
		}
		return null;
	}

	
	/**
	 * 反射设值， 必须存在set方法
	 */
	public static <T, O> void setValue(T target, String field, O value, Class<O> argClass) {
		setValue(target, field, value, argClass, true);
	}
	
	public static <T, O> void setValue(T target, String field, O value, Class<O> argClass, boolean throwException) {
		try {
			Class<? extends Object> targetClass = target.getClass();
			Method method;
			field = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
			method = targetClass.getMethod(field, argClass);
			method.invoke(target, value);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	/**
	 * 反射设值， 必须存在get方法
	 */
	public static <T, K> K getValue(T t, String field) {
		return getValue(t, field, true);
	}

	/**
	 * 会对日期类型做转换
	 * 反射设值， 必须存在get方法
	 */
	public static String getValueToString(Object t, String field) {
		Object value = getValue(t, field, true);
		if(value != null && value instanceof Date){
			String format = getDateFormat(t.getClass(), field);
			return DateUtils.format((Date) value, format);
		}
		return String.valueOf(value);
	}

	private static String getDateFormat(Class<? extends Object> class1, String fieldName){
		String pattern = null;
		JsonFormat format = null;
		Field field = getFieldRecursion(class1, fieldName);
		if(field != null){
			format = field.getAnnotation(JsonFormat.class);
		}
		pattern = format == null? "": format.pattern();
		return pattern;
	}
	
	@SuppressWarnings("unchecked")
	public static <T, K> K getValue(T t, String field, boolean throwException) {
		try {
			Class<? extends Object> class1 = t.getClass();
			Method method;
			field = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
			method = class1.getMethod(field);
			Object object = method.invoke(t);
			return (K) object;
		} catch (Exception e) {
			return null;
		}
	} 
	
	/**
	 * 获取存在某个注解的字段
	 * getSupers： 是否要获取父类的字符
	 */
	public static <T extends Annotation> List<Field> getFieldByAnnotation(Class<?> clazz, Class<T> anno, boolean getSupers){
		List<Field> list = Lists.newArrayList();
		
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null){
			for(Field field : fields){
				list.add(field);
			}
		}
		Class<?> superClazz = clazz.getSuperclass();
		while(superClazz != Object.class && getSupers){
			Field[] superFields = superClazz.getDeclaredFields();
			if(superFields != null){
				for(Field field : superFields){
					list.add(field);
				}
			}
			superClazz = superClazz.getSuperclass();
		}
		
		Iterator<Field> iterator = list.iterator();
		
		while(iterator.hasNext()){
			Field field = iterator.next();
			if(field.getAnnotation(anno) == null){
				iterator.remove();
			}
		}
		
		return list;
	}
	
	public static String getGenericClassName(Class<?> clazz){
		return getGenericClassName(clazz, 0);
	}
	
	public static <T> Class<T> getGenericClass(Class<?> clazz){
		return getGenericClass(clazz, 0);
	}
	
	public static String getGenericClassName(Class<?> clazz, int index){
		return getGenericClass(clazz, index).getSimpleName();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericClass(Class<?> clazz, int index){
		Type type = clazz.getGenericSuperclass();
		if( type instanceof ParameterizedType ){
			ParameterizedType pType = (ParameterizedType)type;
			Type claz = pType.getActualTypeArguments()[index];
			if( claz instanceof Class ){
				clazz = (Class<?>) claz;
			}
		}
		return (Class<T>) clazz;
	}
	
	public static Type getGenericType(Class<?> clazz, int index){
		Type type = clazz.getGenericSuperclass();
		if(type instanceof ParameterizedType ){
			ParameterizedType pType = (ParameterizedType)type;
			type = pType.getActualTypeArguments()[index];
		}
		return type;
	}
	
	public static Type getGenericType(Class<?> clazz){
		return getGenericType(clazz, 0);
	}
	
	/**
	 * 获取存在某个注解的字段
	 * getSupers： 是否要获取父类的字符
	 */
	public static List<Field> getField(Class<?> clazz, boolean getSupers){
		
		List<Field> list = Lists.newArrayList();
		
		Field[] fields = clazz.getDeclaredFields();
		if(fields != null){
			for(Field field : fields){
				list.add(field);
			}
		}
		Class<?> superClazz = clazz.getSuperclass();
		while(superClazz != Object.class && getSupers){
			Field[] superFields = superClazz.getDeclaredFields();
			if(superFields != null){
				for(Field field : superFields){
					list.add(field);
				}
			}
			superClazz = superClazz.getSuperclass();
		}
		
		return list;
	}
	
	/**
	 * 获取方法，只获取公共方法
	 */
	public static Method getPublicMethod(Class<?> clazz, String methodName, Class<?>... classParams){
		try {
			Method method = null;
			if(classParams == null || classParams.length == 0){
				method = clazz.getMethod(methodName);
			}else{
				method = clazz.getMethod(methodName, classParams);
			}
			return method;
		} catch (Exception e) {
		}
		return null;
	}
	
	
	/**
	 * 判断属性是否存在
	 */
	public static boolean existsField(Class<?> clazz, String fieldName){
		return existsField(clazz, fieldName, true);
	}
	
	/**
	 * 判断属性是否存在
	 */
	public static boolean existsField(Class<?> clazz, String fieldName, boolean getSupers){
		if(clazz == null || StringUtils.isEmpty(fieldName)){
			return false;
		}
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
		}
		if(field != null){
			return true;
		}
		Class<?> superClazz = clazz.getSuperclass();
		return existsField(superClazz, fieldName, getSupers);
	}
	
	/**
	 * 获取字段，可以为空
	 * 私有和父类的字段也可以获取
	 */
	public static Field getField(Class<?> clazz, String fieldName){
		try{
			Field[] fields = clazz.getDeclaredFields();
			if(CommonUtils.notEmpty(fields)){
				for(Field field : fields){
					if(field.getName().equals(fieldName)){
						return field;
					}
				}
			}
			Class<?> superClazz = clazz.getSuperclass();
			while(superClazz != Object.class){
				return getField(superClazz, fieldName);
			}
		}catch(Exception e){
			String className = clazz == null? "class为空": clazz.getSimpleName();
			logger.error(String.format("获取字段异常：参数[%s，%s]", className, fieldName), e);
		}
		return null;
	}
	
	/**
	 * 获取字段get方法
	 */
	public static Method getGetMethod(Class<?> clazz, String fieldName){
		
		String getMethodName = "get" + StringUtils.firstUpper(fieldName);
		Method method = getPublicMethod(clazz, getMethodName, (Class<?>[])null);
		if(method != null){
			return method;
		}
			
		Field field = getField(clazz, fieldName);
		if(field == null){
			return null;
		}
			
		Class<?> type = field.getType();
		if(type.equals(boolean.class)){
			getMethodName = "is" + StringUtils.firstUpper(fieldName);
			return getPublicMethod(clazz, getMethodName, (Class<?>[])null);
		}
		
		return null;
	}
	
	/**
	 * 获取字段set方法
	 */
	public static Method getSetMethod(Class<?> clazz, String fieldName){
		Field field = getField(clazz, fieldName);
		if(field == null){
			return null;
		}
		String getMethodName = "set" + StringUtils.firstUpper(fieldName);
		Method method = getPublicMethod(clazz, getMethodName, field.getType());
		return method;
	}
	
	/**
	 * 获取字段get方法
	 */
	public static List<Method> getGetMethod(Class<?> clazz, List<String> fieldNames, boolean nullThrow){
		if(CommonUtils.notEmpty(fieldNames)){
			return getGetMethod(clazz, fieldNames.toArray(new String[fieldNames.size()]), nullThrow);
		}
		return Lists.newArrayList();
	}
	
	/**
	 * 获取字段set方法
	 */
	public static List<Method> getSetMethod(Class<?> clazz, List<String> fieldNames, boolean nullThrow){
		if(CommonUtils.notEmpty(fieldNames)){
			return getSetMethod(clazz, fieldNames.toArray(new String[fieldNames.size()]), nullThrow);
		}
		return Lists.newArrayList();
	}
	
	/**
	 * 获取字段get方法
	 */
	public static List<Method> getGetMethod(Class<?> clazz, String[] fieldNames, boolean nullThrow){
		List<Method> list = Lists.newArrayList();
		for(int i=0; i< CommonUtils.size(fieldNames); i++){
			Method method = getGetMethod(clazz, fieldNames[i]);
			list.add(method);
		}
		return list;
	}
	
	/**
	 * 获取字段set方法
	 */
	public static List<Method> getSetMethod(Class<?> clazz, String[] fieldNames, boolean nullThrow){
		List<Method> list = Lists.newArrayList();
		for(int i=0; i< CommonUtils.size(fieldNames); i++){
			Method method = getSetMethod(clazz, fieldNames[i]);
			list.add(method);
		}
		return list;
	}
	
	/**
	 * 执行get方法
	 */
	public static Object invokeGetMethod(Object obj, Method method){
		Object value = null;
		try {
			value = method.invoke(obj);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 执行set方法
	 */
	public static void invokeSetMethod(Object obj, String value, Method method){
		try {
			method.invoke(obj, value);
		} catch (Exception e) {
		}
	}
	
	/**
	 * 执行get方法
	 */
	public static Object[] invokeGetMethod(Object obj, Method... methods){
		Object value = null;
		Object[] arrs = new Object[methods.length];
		try {
			int i = 0;
			for(Method method : methods){
				value = method.invoke(obj);
				arrs[i++] = value;
			}
		} catch (Exception e) {
		}
		return arrs;
	}
	
	/**
	 * 执行get方法
	 */
	public final static <T> String[] invokeGetMethodToString(T obj, Method... methods){
		Object value = null;
		String[] arrs = new String[methods.length];
		try {
			int i = 0;
			for(Method method : methods){
				value = method.invoke(obj);
				arrs[i++] = String.valueOf(value);
			}
		} catch (Exception e) {
		}
		return arrs;
	}

	/**
	 * 反射设值， 必须存在set方法
	 */
	@SuppressWarnings("unchecked")
	public static <T, V> V getValue(T target, Method method, boolean throwException){
		try{
			Object invoke = method.invoke(target, (Object[])null);
			return (V) invoke;
		}catch(RuntimeException e){
			if(throwException){
				throw e;
			}else{
				logger.warn("执行方法有误", e);
			}
		}catch(Exception e){
			if(throwException){
				throw new RuntimeException(e);
			}else{
				logger.warn("执行方法有误", e);
			}
		}
		return null;
	}
	
	/**
	 * 反射设值，必须存在set方法
	 */
	public static <T, V> boolean setValue(T target, Method method, V value, boolean throwException){
		try{
			method.invoke(target, value);
			return true;
		}catch(RuntimeException e){
			if(throwException){
				throw e;
			}else{
				logger.warn("执行方法有误", e);
			}
		}catch(Exception e){
			if(throwException){
				throw new RuntimeException(e);
			}else{
				logger.warn("执行方法有误", e);
			}
		}
		return false;
	}
	
	/**
     * 获取当前对象【fieldName】属性的值
     */
    public static Object getClassValue(Object obj, String fieldName) throws Exception{
        if (obj == null || StringUtils.isEmpty(fieldName)) return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i  = 0; i < fields.length; i++){
        	if(fieldName.equals(fields[i].getName())){
        		// 获取对象的属性
                Field field = obj.getClass().getDeclaredField(fieldName);
                // 对象的属性的访问权限设置为可访问
                field.setAccessible(true);
                // 返回此属性的值
                return field.get(obj);
        	}
		}
        return null;
    }
    
    /**
     * 设置当前对象【fieldName】属性的值
     */
    public static Object setClassValue(Object obj, String fieldName,String value) throws Exception{
        if (obj == null || StringUtils.isEmpty(fieldName)) return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        for(int i  = 0; i < fields.length; i++){
        	if(fieldName.equals(fields[i].getName())){
        		// 获取对象的属性
                Field field = obj.getClass().getDeclaredField(fieldName);
                // 对象的属性的访问权限设置为可访问
                field.setAccessible(true);
                // 设置此属性的值
                field.set(obj, value);
                return field.get(obj);
        	}
		}
        return null;
    }

	/**
	 * 设置当前对象【fieldName】属性的值
	 */
	public static Object setClassValue(Object obj, String fieldName,Object value) throws Exception{
		if (obj == null || StringUtils.isEmpty(fieldName)) return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		for(int i  = 0; i < fields.length; i++){
			if(fieldName.equals(fields[i].getName())){
				// 获取对象的属性
				Field field = obj.getClass().getDeclaredField(fieldName);
				// 对象的属性的访问权限设置为可访问
				field.setAccessible(true);
				// 设置此属性的值
				field.set(obj, value);
				return field.get(obj);
			}
		}
		return null;
	}

	/**
	 * 新建泛型对象
	 */
	public static <T> T getInstance(T target){
		T t = null;
		try {
			t = (T) target.getClass().getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
    
}
