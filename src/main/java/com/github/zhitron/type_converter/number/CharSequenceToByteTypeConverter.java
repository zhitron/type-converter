package com.github.zhitron.type_converter.number;

import com.github.zhitron.type_converter.CharSequenceTypeConverter;

/**
 * 字符串到Byte类型转换器
 * <p>
 * 用于将字符串转换为Byte类型。
 * 该转换器直接使用Byte的构造函数进行转换。
 * 这是一个单例类，通过INSTANCE字段获取实例。
 * </p>
 *
 * @author zhitron
 */
public class CharSequenceToByteTypeConverter extends CharSequenceTypeConverter<CharSequence, Byte> {
    /**
     * 单例实例
     */
    public static final CharSequenceToByteTypeConverter INSTANCE = new CharSequenceToByteTypeConverter();

    /**
     * 构造函数，初始化目标类型为Byte.class
     */
    protected CharSequenceToByteTypeConverter() {
        super(Byte.class);
    }

    /**
     * 将字符串源对象转换为Byte对象
     *
     * @param input 源对象(字符串类型)
     * @return 转换后的Byte对象
     * @throws Throwable 转换过程中发生错误时抛出
     */
    @Override
    protected Byte convertsUncheckedString(String input) throws Throwable {
        return StringToByteTypeConverter.INSTANCE.convertsUnchecked(input);
    }
}
