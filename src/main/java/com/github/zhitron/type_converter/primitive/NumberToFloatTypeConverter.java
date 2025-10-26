package com.github.zhitron.type_converter.primitive;

import com.github.zhitron.type_converter.TypeConverter;

/**
 * 数字到浮点数的类型转换器
 * <p>
 * 该转换器用于将Number类型转换为Float类型，直接调用Number的floatValue()方法进行转换。
 * 这是一个单例类，通过INSTANCE字段获取实例。
 * </p>
 *
 * @author zhitron
 */
public class NumberToFloatTypeConverter extends TypeConverter<Number, Float> {
    /**
     * 单例实例
     */
    public static final NumberToFloatTypeConverter INSTANCE = new NumberToFloatTypeConverter();

    /**
     * 构造函数，初始化目标类型
     * 使用protected修饰符防止外部直接实例化
     */
    protected NumberToFloatTypeConverter() {
        super(Float.class);
    }

    /**
     * 判断指定的源对象是否支持转换
     * 仅当源对象是Number类型时才支持转换
     *
     * @param source 源对象
     * @return 如果源对象是Number类型返回true，否则返回false
     */
    @Override
    public boolean isSupportsSource(Object source) {
        return source instanceof Number;
    }

    /**
     * 将源对象转换为目标类型对象，不进行源对象类型检查
     * 直接调用Number.floatValue()方法获取浮点数值
     *
     * @param source 源对象，必须是Number类型
     * @return 转换后的Float对象
     * @throws NullPointerException 当source为null时抛出
     */
    @Override
    public Float convertsUnchecked(Number source) throws Throwable {
        return source.floatValue();
    }
}
