package com.zhangds.common.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.time.DateUtils.setMilliseconds;
import static org.apache.commons.lang3.time.DateUtils.setSeconds;

/**
 * 基类 Base
 * 
 */
public class CommonUtils {
	
	private final static String[] EMPTY_ARRAY = new String[0];
	
	private final static Log log = LogFactory.getLog(com.zhangds.common.util.CommonUtils.class);

    /**
     * 获取日期
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 获取日期
     * @return
     */
    public static Date getMinuteDate() {
        return setSeconds(setMilliseconds(getDate(), 0), 0);
    }
    
    /**
     * 非空格
     * @param var
     * @return
     */
    public static boolean notBlank(String var) {
        return StringUtils.isNotBlank(var);
    }

    /**
     * 非空格
     * @param var
     * @return
     */
    public static boolean blank(String var) {
    	return StringUtils.isBlank(var);
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(String var) {
        return isNotBlank(var);
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(String var) {
        return isBlank(var);
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(long var) {
        return 0 != var;
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(long var) {
        return 0 == var;
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(int var) {
        return 0 != var;
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(int var) {
        return 0 == var;
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(Object var) {
        return null != var;
    }
    
    /**
     * 非空或者非空字符串
     * @return
     */
    public static boolean notEmptyOrBlank(Object obj) {
    	if(obj == null){
    		return false;
    	}
    	if(obj instanceof CharSequence){
    		return StringUtils.isNotBlank((CharSequence)obj);
    	}else if(obj instanceof Collection){
    		return notEmpty((Collection<?>)obj);
    	}else if(obj instanceof Map){
    		return notEmpty((Map<?, ?>)obj);
    	}else if(obj instanceof Object[]){
    		return notEmpty((Object[])obj);
    	}
    	return true;
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(Object var) {
        return null == var;
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(Collection<?> var) {
        return null != var && !var.isEmpty();
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(Collection<?> var) {
        return null == var || var.isEmpty();
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(Map<?, ?> var) {
        return null != var && !var.isEmpty();
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(Map<?, ?> var) {
        return null == var || var.isEmpty();
    }

    /**
     * 非空
     * @param file
     * @return
     */
    public static boolean notEmpty(File file) {
        return null != file && file.exists();
    }

    /**
     * 空
     * @param file
     * @return
     */
    public static boolean empty(File file) {
        return null == file || !file.exists();
    }

    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(Object[] var) {
        return null != var && 0 < var.length;
    }

    /**
     * 空
     * @param var
     * @return
     */
    public static boolean empty(Object[] var) {
        return null == var || 0 == var.length;
    }
    
    /**
     * 非空
     * @param var
     * @return
     */
    public static boolean notEmpty(Set<?> var) {
        return null != var && !var.isEmpty();
    }

    public static boolean empty(Set<?> var) {
        return null == var || var.isEmpty();
    }

    @SuppressWarnings("unchecked")
	public static <T extends Serializable> T deepClone(T a){
    	T t = null;
    	if(a != null){
    		try{
    			ByteArrayOutputStream baos = new ByteArrayOutputStream();
    			ObjectOutputStream oos = new ObjectOutputStream(baos);
    			oos.writeObject(a);
    			oos.flush();
    			oos.close();
    			byte[] arrByte = baos.toByteArray();
    			ByteArrayInputStream bais = new ByteArrayInputStream(arrByte);
    			ObjectInputStream ois = new ObjectInputStream(bais);
    			t = (T)ois.readObject();
    			ois.close();
    		}catch(Exception e){
    		}
    	}
	    return t;
	}
    
    /**
     * 空对象转为空字符串
     * author lijuntao
     * date 2017年12月7日
     */
    public static String nullToEmpty(String str){
    	return str == null?"":str;
    }
    
    /**
     * 空对象转为空字符串
     * author lijuntao
     * date 2017年12月7日
     */
    public static Long nullToEmpty(Long long1){
    	return long1 == null?Long.valueOf(0):long1;
    }
    
    /**
     * 空集合对象转为无元素的空集合
     * author lijuntao
     * date 2017年12月7日
     */
    public static <T> List<T> nullToEmpty(List<T> list){
    	return list == null? Lists.<T>newArrayList():list;
    }
    
    /**
     * Null转为无元素的Map对象
     * author lijuntao
     * date 2017年12月7日
     */
    public static <K, V> Map<K, V> nullToEmpty(Map<K, V> map){
    	return map == null? Maps.<K, V>newHashMap():map;
    } 
    
	public static String[] nullToEmpty(String[] lastIgnores) {
		return lastIgnores == null?new String[0]:lastIgnores;
	}
	
	/**
	 * 为避免空对象循环异常
	 * author lijuntao
	 * date 2017年12月7日
	 */
	public static <T> String[] nullToEmptyForEach(String[] strs){
		return strs == null? EMPTY_ARRAY:strs;
	}
	
	/**
	 * 为避免空对象循环异常
	 * author lijuntao
	 * date 2017年12月7日
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> nullToEmptyForEach(Set<T> set){
		return set == null?Collections.EMPTY_SET:set;
	}

    public static <K, V> Map<K, V> nullToEmptyForEach(Map<K, V> map){
        return map == null? Collections.emptyMap(): map;
    }

    /**
	 * 空集合对象转为无元素的空集合
	 * author lijuntao
	 * date 2017年12月7日
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> nullToEmptyForEach(List<T> collection){
		return collection == null?Collections.EMPTY_LIST:collection;
	} 
	
	/**
	 * 空集合对象转为无元素的空集合
	 * author lijuntao
	 * date 2017年12月7日
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> nullToEmptyForEach(Collection<T> collection){
		return collection == null?Collections.EMPTY_LIST:collection;
	} 
	
	/**
	 * 空集合对象转为无元素的空集合
	 * author lijuntao
	 * date 2017年12月7日
	 */
	public static <T> Collection<T> nullToEmpty(Collection<T> collection){
		return collection == null? Lists.<T>newArrayList():collection;
	} 
	
    /**
     * 获取字符串最大长度
     * author lijuntao
     * date 2017年12月7日
     */
    public static String subMaxString(String str, int maxLength){
    	return (str == null || str.length() <= maxLength)?str:str.substring(0, maxLength);
    }
    
    /**
     * 获取字符串最大长度，从后往前截取
     * author lijuntao
     * date 2017年12月7日
     */
    public static String subMaxStringFromBack(String str, int maxLength){
    	return (str == null || str.length() <= maxLength)?str:str.substring(str.length()-maxLength, str.length());
    }
    
    /**
     * 空集合对象转为无元素的空集合
     * author lijuntao
     * date 2017年12月7日
     */
    public static <T> Set<T> nullToEmpty(Set<T> set){
    	return set == null? Sets.<T>newHashSet():set;
    }


    /**
     * 求两个时间的时间差
     * 返回时间单位：分
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     */
    public static long getDistanceTime(String str1, String str2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long result=0;
        boolean equalResult=false;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
                equalResult=true;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (true==equalResult){
            result =(day * 24 * 60 +hour * 60 +min + sec / 60) * (-1) ;
        }
       else{
             result= day * 24 * 60 +hour * 60 +min + sec / 60;
        }
        return result;
    }

    public  static  String DateToStr(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
         String str=sdf.format(date);
         return str;
    }

    public  static  String DateToStrDate(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");;
        String str=sdf.format(date);
        return str;
    }
    public  static  Date StrToDate(String str) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public  static  Date StrDateToDate(String str) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date= null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    public static String getStackMessage(Exception e){
		try {
			ByteArrayOutputStream opStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(opStream, false, "utf-8"));
			return opStream.toString("utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return "";
	}
    
    /**
     * 比较实现Comparable接口的类的两个对象
     * 如果其中某个为空，另一个非空， 则根据nullFirst， 来决定空对象和非空对象的大小
     * @Param nullFirst：当为true， 代表空对象排在前面， 空对象要比非空对象小
     * author lijuntao
     * date 2018年4月10日
     */
    public static <T> int compare(Comparable<T> s1, T s2, boolean nullFirst, boolean desc){
    	if(s1 == null){
			if(s2 == null){
				return 0;
			}
			return nullFirst?-1:1;
		}else{
			if(s2 == null){
				return nullFirst?1:-1;
			}
			return desc? 0 - s1.compareTo(s2): s1.compareTo(s2);
		}
    }
    
    /**
     * 比较实现Comparable接口的类的两个对象
     * 如果其中某个为空，另一个非空， 则根据nullFirst， 来决定空对象和非空对象的大小
     * @Param nullFirst：当为true， 代表空对象排在前面， 空对象要比非空对象小
     * author lijuntao
     * date 2018年4月10日
     */
    public static <T> int compare(Comparable<T> s1, T s2, boolean nullFirst){
    	return compare(s1, s2, nullFirst, false);
    }
    
    /**
     * 默认空顺序排在最后
     * author lijuntao
     * date 2018年4月2日
     */
    public static <T> int compare(Comparable<T> s1, T s2){
    	return compare(s1, s2, false, false);
    }
    
    /**
     * 获取最小值，忽略空值
     * author lijuntao
     * date 2018年4月2日
     */
    public static <T> T minIgnoreNull(List<T> list, Comparator<T> compare){
    	if(com.zhangds.common.util.CommonUtils.size(list) == 0){
    		return null;
    	}else if(com.zhangds.common.util.CommonUtils.size(list) == 1){
    		return list.get(0);
    	}
    	T min = null;
    	for(int i = 0; i< com.zhangds.common.util.CommonUtils.size(list); i++){
    		T temp = list.get(i);
    		if(min == null){
    			min = temp;
    		}else if(temp != null && compare.compare(min, temp) > 0){
    			min = temp;
    		}
    	}
    	return min;
    }
    
    /**
     * 获取最大值，忽略空值
     * author lijuntao
     * date 2018年4月2日
     */
    public static <T> T maxIgnoreNull(List<T> list, Comparator<T> compare){
    	if(com.zhangds.common.util.CommonUtils.size(list) == 0){
    		return null;
    	}else if(com.zhangds.common.util.CommonUtils.size(list) == 1){
    		return list.get(0);
    	}
    	T min = null;
    	for(int i = 0; i< com.zhangds.common.util.CommonUtils.size(list); i++){
    		T temp = list.get(i);
    		if(min == null){
    			min = temp;
    		}else if(temp != null && compare.compare(min, temp) < 0){
    			min = temp;
    		}
    	}
    	return min;
    }
    
    
    /**
     * 默认空顺序排在最后
     * author lijuntao
     * date 2018年4月2日
     */
    public static <T> int compareNull(T s1, T s2){
    	return compareNull(s1, s2, false);
    }
    
    /**
     * 默认空顺序排在最后
     * author lijuntao
     * date 2018年4月2日
     */
    public static <T> int compareNull(T s1, T s2, boolean nullFirst){
    	if(s1 == null && s2 == null){
			return 0;
		}
    	if(s1 != null && s2 != null){
    		return 0;
    	}
    	if(s1 == null){
    		return nullFirst?-1:1;
    	}
    	return nullFirst?1:-1;
    }
    
    public static String subTextString(String str,int len){
        if(str.length()<len/2)
            return str;
        int count = 0;
        StringBuffer sb = new StringBuffer();
        String[] ss = str.split("");
        for(int i=0;i<ss.length;i++){
            count+=ss[i].getBytes().length>1?2:1;
            sb.append(ss[i]);
            if(count>=len)break;
        }
        //不需要显示...的可以直接return sb.toString();(sb.toString().length()<str.length())?sb.append("...").toString():str
        return sb.toString();
    }
    
    /**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     * author lijuntao
     * date 2018年4月16日
     */
    public static Integer parseInteger(String str){
    	if(com.zhangds.common.util.CommonUtils.empty(str))
    		return null;
    	try{
    		return Integer.parseInt(str);
    	}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Integer失败", str));
    	}
    	return null;
    }

    /**
     * 字符串解析成长整型数字， 不报错
     * 解析失败为空
     * author sugx
     * date 2020年5月20日
     */
    public static Long parseLong(String str){
        if(com.zhangds.common.util.CommonUtils.empty(str))
            return null;
        try{
            return Long.parseLong(str);
        }catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Long失败", str));
        }
        return null;
    }

    
    /**
     * 字符串解析成数字， 不报错
     * 解析失败为空
     * author lijuntao
     * date 2018年4月16日
     */
    public static Double parseDouble(String str){
    	if(com.zhangds.common.util.CommonUtils.empty(str))
    		return null;
    	try{
    		return Double.parseDouble(str);
    	}catch(Exception e){
//    		log.error(String.format("[%s]字符串解析成Double失败", str));
    	}
    	return null;
    }

    public static int minusDays(Date date1, Date date2){
    	if(empty(date1) || empty(date2)){
    		throw new RuntimeException("执行两个日期的相差天数：日期不能为空");
    	}
    	//除去时分秒
    	Calendar c1 = Calendar.getInstance();
    	c1.setTime(date1);
    	c1.set(Calendar.HOUR_OF_DAY, 0);
    	c1.set(Calendar.MINUTE, 0);
    	c1.set(Calendar.SECOND, 0);
    	c1.set(Calendar.MILLISECOND, 0);
    	//除去时分秒
    	Calendar c2 = Calendar.getInstance();
    	c2.setTime(date2);
    	c2.set(Calendar.HOUR_OF_DAY, 0);
    	c2.set(Calendar.MINUTE, 0);
    	c2.set(Calendar.SECOND, 0);
    	c2.set(Calendar.MILLISECOND, 0);
    	long millis1 = c1.getTimeInMillis();
    	long millis2 = c2.getTimeInMillis();
    	long minus = millis1 - millis2;
    	long oneDayMillis = 24 * 60 * 60 * 1000;
    	int days = (int) (minus / oneDayMillis);
    	return days;
    }

    public static List<String> subString(String str, int notAsciiWorthSize, int length){
    	if(notAsciiWorthSize < 1)
    		throw new RuntimeException("notAsciiWorthSize 必须大于或者等于1");
    	if(length < 1)
    		throw new RuntimeException("length 必须大于或者等于1");
    	if(com.zhangds.common.util.CommonUtils.empty(str))
    		return Lists.newArrayList();
    	
    	List<String> list = Lists.newArrayList();
    	char[] cs = str.toCharArray();
    	
    	//积累了多少长度
    	int accumulative = 0;
    	//上次截取完后的下标
    	int lastSubIndex = 0;
    	//每个字符长度的大小
    	int evenyCharWorthSize = 0;
    	
    	for(int i=0; i< cs.length; i++){
    		if(-128 <= cs[i] && cs[i] <= 127){
    			evenyCharWorthSize = 1;
    		}else{
    			evenyCharWorthSize = notAsciiWorthSize;
    		}
    		
    		accumulative += evenyCharWorthSize;
    		
    		if(accumulative == length){
    			//刚好匹配
    			char[] subCs = new char[i - lastSubIndex + 1];
    			System.arraycopy(cs, lastSubIndex, subCs, 0, i - lastSubIndex + 1);
    			list.add(new String(subCs));
    			accumulative = 0;
    			lastSubIndex = i + 1;
    		}else if(accumulative < length){
    			if(i == cs.length - 1){
    				//刚好最后
    				char[] subCs = new char[cs.length - lastSubIndex];
        			System.arraycopy(cs, lastSubIndex, subCs, 0, cs.length - lastSubIndex);
        			list.add(new String(subCs));
    			}
    		}else{
    			if(i > lastSubIndex){
	    			char[] subCs = new char[i - lastSubIndex];
	    			System.arraycopy(cs, lastSubIndex, subCs, 0, i - lastSubIndex);
	    			list.add(new String(subCs));
	    			lastSubIndex = i;
	    			accumulative = evenyCharWorthSize;
    			}
    			
    			if(i == cs.length - 1){
    				//刚好最后
    				list.add(new String(new char[]{cs[i]}));
    			}
    		}
    		
    	}
    	
    	return list;
    }

	public static <T extends Serializable> List<T> deepCloneList(List<T> list) {
		List<T> clones  = Lists.newArrayList();
		for(T t : nullToEmpty(list)){
			T clone = deepClone(t);
			clones.add(clone);
		}
		return clones;
	}

	public static <T> T getOne(List<T> list) {
		return notEmpty(list)? list.get(0): null;
	}
	
	public static <T> T getLast(List<T> list) {
		return notEmpty(list)? list.get(com.zhangds.common.util.CommonUtils.size(list) - 1): null;
	}
	
	public static <T> int size(Collection<T> list){
		return list == null ? 0:list.size();
	}
	
	public static <K, V> int size(Map<K, V> map) {
		return map == null ? 0:map.size();
	}
	
	public static <T> int size(T[] arrs){
		return arrs == null ? 0:arrs.length;
	}

	public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2){
		Map<K, V> mergeMap = Maps.newHashMap();
		if(notEmpty(map1)){
			for(Entry<K, V> entry : map1.entrySet()){
				mergeMap.put(entry.getKey(), entry.getValue());
			}
		}
		if(notEmpty(map2)){
			for(Entry<K, V> entry : map2.entrySet()){
				mergeMap.put(entry.getKey(), entry.getValue());
			}
		}
		return mergeMap;
	}

	public static <K, T> Map<K, Collection<T>> addTo(Map<K, Collection<T>> map, K key, T entity){
		if(map != null && key != null && entity != null){
			Collection<T> list = map.get(key);
			if(list == null){
				synchronized (map) {
					if(list == null){
						list = new ArrayList<T>();
					}
					map.put(key, list);
				}
			}
			list.add(entity);
		}
		return map;
	}
	
	public static <K, T> Map<K, List<T>> addToList(Map<K, List<T>> map, K key, T entity){
		if(map != null && key != null && entity != null){
			List<T> list = map.get(key);
			if(list == null){
				synchronized (map) {
					if(list == null){
						list = new ArrayList<T>();
					}
					map.put(key, list);
				}
			}
			list.add(entity);
		}
		return map;
	}
	
	/*
	 * Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	 */
	public static <T> Map<String, Object> toMap(T obj) {
	    if(obj == null){
	        return Maps.newHashMap();
	    }        
	    Map<String, Object> map = Maps.newHashMap();
	    try {
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor property : propertyDescriptors) {
	            String key = property.getName();
	            // 过滤class属性
	            if (!key.equals("class")) {
	                // 得到property对应的getter方法
	                Method getter = property.getReadMethod();
	                Object value = getter.invoke(obj);
	                map.put(key, value);
	            }
	
	        }
	    } catch (Exception e) {
	        log.error("toMap Error ", e);
	    }
	    return map;
	}

	public static <T extends Comparable<? super T>> void sort(List<T> list){
		if(com.zhangds.common.util.CommonUtils.empty(list)){
			return;
		}
		Collections.sort(list);
	}
	
	public static <T> void sort(List<T> list, Comparator<T> c){
		if(com.zhangds.common.util.CommonUtils.empty(list) || com.zhangds.common.util.CommonUtils.empty(c)){
			return;
		}
		Collections.sort(list, c);
	}

    public static <T> void sort(List<T> list, Comparator<T>... cs){
        if(com.zhangds.common.util.CommonUtils.empty(list) || com.zhangds.common.util.CommonUtils.empty(cs)){
            return;
        }
        Collections.sort(list, (t1, t2) -> {
            int i = 0;
            for(Comparator c : cs){
                i = c.compare(t1, t2);
                if(i != 0){
                    return i;
                }
            }
            return i;
        });
    }
    
    public static <T> boolean contains(List<T> list, T record){
    	if(com.zhangds.common.util.CommonUtils.empty(list) || record == null){
    		return false;
    	}
    	for(T t : list){
    		if(record.equals(t)){
    			return true;
    		}
    	}
    	return false;
    }

    public static <T> List<T> toList(T[] arrs){
    	List<T> list = Lists.newArrayList();
    	if(com.zhangds.common.util.CommonUtils.notEmpty(arrs)){
    		for(T record : arrs){
    			list.add(record);
    		}
    	}
    	return list;
    }
    
    public static <T> Set<T> toSet(T[] arrs){
    	Set<T> set = Sets.newHashSet();
    	if(com.zhangds.common.util.CommonUtils.notEmpty(arrs)){
    		for(T record : arrs){
    			set.add(record);
    		}
    	}
    	return set;
    }

    public static <T> T[] toArray(List<T> idList,Class<T> type){
        T[] arr=(T[])Array.newInstance(type, idList.size());
        idList.toArray(arr);
        return arr;
    }

    public static <T> T[] toArray(Set<T> idsets,Class<T> type){
        T[] arr=(T[])Array.newInstance(type, idsets.size());
        idsets.toArray(arr);
        return arr;
    }

	public static <T> T defaultValue(T integer, T defaultValue){
		return integer != null? integer: defaultValue;
	}

	public static <T> List<T> subList(List<T> list, int beginIndex, int endIndex){
		
		int size = com.zhangds.common.util.CommonUtils.size(list);
		if(beginIndex >= size){
			return Lists.newArrayList();
		}
		endIndex = size < endIndex? size: endIndex;
		return Lists.newArrayList(list.subList(beginIndex, endIndex));
	}

	public static <T> List<List<T>> splitList(List<T> list, int size){
		
		List<List<T>> splitList = Lists.newArrayList();
		int splitSize = com.zhangds.common.util.CommonUtils.size(list) / size;
		if(com.zhangds.common.util.CommonUtils.size(list) % size > 0){
			splitSize += 1;
		}
		for(int i=0; i< splitSize; i++){
			List<T> subList = com.zhangds.common.util.CommonUtils.subList(list, i * size, (i + 1) * size);
			splitList.add(subList);
		}
		return splitList;
	}

	public static <T> T getOne(List<T> list, int index){
		if(com.zhangds.common.util.CommonUtils.size(list) > index){
			return list.get(index);
		}
		return null;
	}

	public static <T> List<List<T>> splitSet(Set<T> set, int size){
		if(com.zhangds.common.util.CommonUtils.empty(set)){
			return Lists.newArrayList();
		}
		List<T> list = set.stream().collect(Collectors.toList());
		List<List<T>> splitList = splitList(list, size);
		return splitList;
	}
	

	public static <K, V> void removeKeyInSet(Map<K, V> map, Set<K> set){
		if(com.zhangds.common.util.CommonUtils.empty(map) || com.zhangds.common.util.CommonUtils.empty(set)){
			return;
		}
		for(K key : set){
			map.remove(key);
		}
	}
	
	public static <K, V> void removeKeyNotInSet(Map<K, V> map, Set<K> set){
		if(com.zhangds.common.util.CommonUtils.empty(map)){
			return;
		}
		if(com.zhangds.common.util.CommonUtils.empty(set)){
			map.clear();
			return;
		}
		Iterator<Entry<K, V>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<K, V> entry = iterator.next();
			if(!set.contains(entry.getKey())){
				iterator.remove();
			}
		}
	}

	/**
	 * 数组类型转化
	 * @param targetType 目标数组类型
	 * @param arrayObjects 传入数组
	 * @param <T>
	 * @return
	 */
	public static <T> T[] convertArray(Class<T> targetType, Object[] arrayObjects) {
		if (targetType == null) {
			return (T[]) arrayObjects;
		}
		if (arrayObjects == null) {
			return null;
		}
		T[] targetArray = (T[]) Array.newInstance(targetType, arrayObjects.length);
		try {
			System.arraycopy(arrayObjects, 0, targetArray, 0, arrayObjects.length);
		} catch (ArrayStoreException e) {
			e.printStackTrace();
		}
		return targetArray;
	}

	public static String toString(Object object){
	    if(object!=null){
	        return String.valueOf(object);
        }
        return null;
    }

    public static <T, R> Set<R> getDistinctSet(Collection<T> co, Function<T, R> function) {
        Set<R> repeats = Sets.newHashSet();
        Set<R> sets = Sets.newHashSet();
        for(T record : co){
            R value = function.apply(record);
            if (!sets.add(value)){
                repeats.add(value);
            }
        }
        return repeats;
    }



}
