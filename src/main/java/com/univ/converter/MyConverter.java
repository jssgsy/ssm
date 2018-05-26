package com.univ.converter;

import org.springframework.core.convert.converter.Converter;

/** 
 * @author univ 
 * @date 2016年1月12日 下午3:40:39 
 * @version v1.0
 * @Description: 自定义类型转换器,为了不对其他功能产生影响，这里拷贝官网api里的代码
 */
final public class MyConverter implements Converter<String, Character>{

	public Character convert(String source) {
		if (source.length() == 0) {
			return null;
		}
		if (source.length() > 1) {
			throw new IllegalArgumentException(
					"Can only convert a [String] with length of 1 to a [Character]; string value '" + source + "'  has length of " + source.length());
		}
		return source.charAt(0);
	}

}

