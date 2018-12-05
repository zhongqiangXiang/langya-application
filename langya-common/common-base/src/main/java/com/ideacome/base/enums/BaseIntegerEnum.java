package com.ideacome.base.enums;


/**
 * <p>
 * 功能介绍: 基础枚举类 用于实现 如果想获取某个枚举的value 直接调用
 * {@link EnumUtils#getValue(Class, Integer)}
 * 
 * <p>
 * 这里为什么要建立额外的一个 Integer 而不是用泛型实现呢？
 * <p>
 * 在使用 {@link EnumUtils}的时候 如果使用泛型 无法实现其获得值，所以暂时采用了新建两个的方法，如果后续有大神发现可以用泛型，记得是实现下
 * 很关键！
 *
 * @author 罗成
 */
public interface BaseIntegerEnum {
	/**
	 * <p>
	 * 方法介绍: 获取当前枚举的key
	 *
	 * <p>
	 * 注意事项：无
	 *
	 * @author 罗成
	 * @return
	 */
	Integer getKey();

	/**
	 * <p>
	 * 方法介绍: 获取当前枚举的value
	 *
	 * <p>
	 * 注意事项：无
	 *
	 * @author 罗成
	 * @return
	 */
	String getValue();

}
