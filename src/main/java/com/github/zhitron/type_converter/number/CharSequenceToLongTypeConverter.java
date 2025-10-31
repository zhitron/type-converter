package com.github.zhitron.type_converter.number;

import com.github.zhitron.type_converter.CharSequenceTypeConverter;

/**
 * 字符串到Long类型转换器
 * <p>
 * 用于将字符串转换为Long类型。
 * 该转换器直接使用Long的构造函数进行转换。
 * 这是一个单例类，通过INSTANCE字段获取实例。
 * </p>
 *
 * @author zhitron
 */
public class CharSequenceToLongTypeConverter extends CharSequenceTypeConverter<CharSequence, Long> {
    /**
     * 单例实例
     */
    public static final CharSequenceToLongTypeConverter INSTANCE = new CharSequenceToLongTypeConverter();

    /**
     * 构造函数，初始化目标类型为Long.class
     */
    protected CharSequenceToLongTypeConverter() {
        super(Long.class);
    }

    /**
     * 将字符串源对象转换为Long对象
     *
     * @param input 源对象(字符串类型)
     * @return 转换后的Long对象
     * @throws Throwable 转换过程中发生错误时抛出
     */
    @Override
    protected Long convertsUncheckedString(String input) throws Throwable {
        return StringToLongTypeConverter.INSTANCE.convertsUnchecked(input);
    }
}
