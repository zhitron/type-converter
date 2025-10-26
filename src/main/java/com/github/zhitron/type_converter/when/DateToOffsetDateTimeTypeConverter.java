package com.github.zhitron.type_converter.when;

import com.github.zhitron.type_converter.TypeConverterException;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;

/**
 * Date转OffsetDateTime类型转换器。
 * <p>
 * 该转换器用于将{@link Date}类型的对象转换为{@link OffsetDateTime}类型。
 * {@link Date}表示自1970年1月1日00:00:00 UTC以来的毫秒数，而{@link OffsetDateTime}表示带有时区偏移量的日期时间。
 * 转换时需要提供一个时区偏移量，用于指定OffsetDateTime的时区偏移。
 * </p>
 *
 * @author zhitron
 */
public class DateToOffsetDateTimeTypeConverter extends AbstractWhenTypeConverter<Date, OffsetDateTime> {
    /**
     * 构造函数，使用指定的时区偏移字符串创建转换器。
     * <p>
     * 该构造函数接受一个时区偏移量字符串，并将其解析为时区偏移对象。
     * 字符串格式应符合ISO-8601标准，例如："+8"、"UTC+8" 或 "+08:00"。
     * </p>
     *
     * @param offset 时区偏移量字符串，例如 "+8"、"UTC+8" 或 "+08:00" 等符合ISO-8601标准的格式。
     * @throws NullPointerException 如果offset为null，则抛出空指针异常。
     */
    public DateToOffsetDateTimeTypeConverter(String offset) {
        super(OffsetDateTime.class, Objects.requireNonNull(offset));
    }

    /**
     * 构造函数，使用指定的ZoneOffset创建转换器。
     * <p>
     * 该构造函数接受一个ZoneOffset对象作为时区偏移量。
     * ZoneOffset表示与UTC时间的固定偏移量。
     * </p>
     *
     * @param zoneOffset ZoneOffset对象，指定转换时使用的时区偏移量。
     * @throws NullPointerException 如果zoneOffset为null，则抛出空指针异常。
     */
    public DateToOffsetDateTimeTypeConverter(ZoneOffset zoneOffset) {
        super(OffsetDateTime.class, Objects.requireNonNull(zoneOffset));
    }

    /**
     * 判断指定的源对象是否支持转换。
     * <p>
     * 当{@link #supportsSourceType()}返回null时，会调用此方法进行判断。
     * 该方法检查源对象是否为Date类型实例。
     * </p>
     *
     * @param source 源对象，需要判断是否支持转换的对象。
     * @return 如果源对象是Date类型实例返回true，否则返回false。
     */
    @Override
    public boolean isSupportsSource(Object source) {
        return source instanceof Date;
    }

    /**
     * 将源Date对象转换为OffsetDateTime对象，不进行源对象类型检查。
     * <p>
     * 该方法首先将Date转换为Instant，然后通过OffsetDateTime.ofInstant()方法
     * 将Instant结合指定的时区偏移量转换为OffsetDateTime。
     * </p>
     *
     * @param source 源Date对象，表示自1970年1月1日00:00:00 UTC以来的毫秒数。
     * @return 转换后的OffsetDateTime对象，表示带有时区偏移量的日期时间；如果source为null则返回null。
     * @throws TypeConverterException 转换过程中发生错误时抛出类型转换异常。
     */
    @Override
    public OffsetDateTime convertsUnchecked(Date source) throws Throwable {
        return OffsetDateTime.ofInstant(source.toInstant(), getZoneOffset());
    }
}
