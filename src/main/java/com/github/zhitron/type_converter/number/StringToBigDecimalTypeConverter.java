package com.github.zhitron.type_converter.number;

import com.github.zhitron.type_converter.TypeConverter;
import com.github.zhitron.type_converter.TypeConverterException;

import java.math.BigDecimal;

/**
 * 字符串到BigDecimal类型转换器
 * <p>
 * 用于将字符串转换为BigDecimal类型。
 * 该转换器直接使用BigDecimal的构造函数进行转换。
 * 这是一个单例类，通过INSTANCE字段获取实例。
 * </p>
 *
 * @author zhitron
 */
public class StringToBigDecimalTypeConverter extends TypeConverter<String, BigDecimal> {
    /**
     * 单例实例
     */
    public static final StringToBigDecimalTypeConverter INSTANCE = new StringToBigDecimalTypeConverter();

    /**
     * 构造函数，初始化目标类型为BigDecimal.class
     */
    protected StringToBigDecimalTypeConverter() {
        super(BigDecimal.class);
    }

    /**
     * 判断指定的源对象是否支持转换
     * 仅当源对象是String类型时才支持转换
     *
     * @param source 源对象
     * @return 如果源对象是String类型返回true，否则返回false
     */
    @Override
    public boolean isSupportsSource(Object source) {
        return source instanceof String;
    }

    /**
     * 将字符串源对象转换为BigDecimal对象
     *
     * @param source 源对象(字符串类型)
     * @return 转换后的BigDecimal对象
     * @throws TypeConverterException 转换过程中发生错误时抛出
     */
    @Override
    public BigDecimal convertsUnchecked(String source) throws Throwable {
        return new BigDecimal(source);
    }
}
