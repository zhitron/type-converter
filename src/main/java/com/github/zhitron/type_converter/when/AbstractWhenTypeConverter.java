package com.github.zhitron.type_converter.when;

import com.github.zhitron.type_converter.TypeConverter;

import java.time.DateTimeException;
import java.time.ZoneOffset;

/**
 * 抽象类型转换器基类，支持指定时区偏移量的类型转换
 * <p>
 * 该类为所有需要时区偏移量支持的类型转换器提供基础实现，
 * 允许在转换过程中考虑时区因素，特别适用于时间相关的类型转换。
 *
 * @param <SourceType> 源数据类型
 * @param <TargetType> 目标数据类型
 * @author zhitron
 */
public abstract class AbstractWhenTypeConverter<SourceType, TargetType> extends TypeConverter<SourceType, TargetType> {
    /**
     * 用于转换的时区偏移量
     * <p>
     * 该字段存储了转换器使用的时区偏移信息，可以在转换过程中使用此信息
     * 来处理与时区相关的转换逻辑。该值可能为null，表示不使用特定时区。
     */
    protected final ZoneOffset zoneOffset;

    /**
     * 默认构造函数，创建不指定时区偏移量的转换器实例
     * <p>
     * 此构造函数调用四参数版本，传入null值表示不使用特定时区偏移量。
     * 这适用于不需要考虑时区因素的类型转换场景。
     *
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     */
    public AbstractWhenTypeConverter(Class<TargetType> targetType) {
        this(targetType, null, null);
    }

    /**
     * 基于时区偏移字符串的构造函数，创建指定时区偏移量的转换器实例
     * <p>
     * 根据提供的时区偏移量字符串创建ZoneOffset对象。该构造函数适用于需要根据
     * 字符串形式的时区信息进行转换的场景。
     *
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param offset     时区偏移量字符串，例如 "+8"、"UTC+8" 或 "+08:00" 等符合ISO-8601标准的格式
     * @throws NullPointerException 当offset参数为null时抛出（虽然方法本身不直接抛出，但ZoneOffset.of可能会）
     * @throws DateTimeException    当offset参数格式无效或不符合ISO-8601标准时抛出
     */
    public AbstractWhenTypeConverter(Class<TargetType> targetType, String offset) {
        this(targetType, offset, offset == null ? null : ZoneOffset.of(offset));
    }

    /**
     * 基于ZoneOffset对象的构造函数，创建指定时区偏移量的转换器实例
     * <p>
     * 直接使用提供的ZoneOffset对象进行初始化。这是最直接的方式，适用于已经拥有
     * ZoneOffset对象的场景。
     *
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param zoneOffset ZoneOffset对象，指定转换时使用的时区偏移量
     * @throws NullPointerException 当zoneOffset参数为null时抛出（虽然方法本身不直接抛出，但内部逻辑会处理）
     */
    public AbstractWhenTypeConverter(Class<TargetType> targetType, ZoneOffset zoneOffset) {
        this(targetType, zoneOffset == null ? null : zoneOffset.getId(), zoneOffset);
    }

    /**
     * 私有的完整构造函数，用于初始化转换器的所有基本配置
     * <p>
     * 这是实际执行初始化工作的构造函数，其他公共构造函数最终都会调用此方法。
     * 它负责设置父类的配置参数并将ZoneOffset保存到当前实例中。
     *
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param offset     时区偏移量字符串，可能为null
     * @param zoneOffset ZoneOffset对象，可能为null
     */
    protected AbstractWhenTypeConverter(Class<TargetType> targetType, String offset, ZoneOffset zoneOffset) {
        super(targetType, offset, zoneOffset, zoneOffset == null ? null : zoneOffset.getId());
        this.zoneOffset = zoneOffset;
    }

    /**
     * 默认构造函数，创建不指定时区偏移量的转换器实例
     * <p>
     * 此构造函数调用四参数版本，传入null值表示不使用特定时区偏移量。
     * 这适用于不需要考虑时区因素的类型转换场景。
     *
     * @param sourceType 源数据类型的Class对象，用于类型检查和识别
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     */
    public AbstractWhenTypeConverter(Class<SourceType> sourceType, Class<TargetType> targetType) {
        this(sourceType, targetType, null, null);
    }

    /**
     * 基于时区偏移字符串的构造函数，创建指定时区偏移量的转换器实例
     * <p>
     * 根据提供的时区偏移量字符串创建ZoneOffset对象。该构造函数适用于需要根据
     * 字符串形式的时区信息进行转换的场景。
     *
     * @param sourceType 源数据类型的Class对象，用于类型检查和识别
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param offset     时区偏移量字符串，例如 "+8"、"UTC+8" 或 "+08:00" 等符合ISO-8601标准的格式
     * @throws NullPointerException 当offset参数为null时抛出（虽然方法本身不直接抛出，但ZoneOffset.of可能会）
     * @throws DateTimeException    当offset参数格式无效或不符合ISO-8601标准时抛出
     */
    public AbstractWhenTypeConverter(Class<SourceType> sourceType, Class<TargetType> targetType, String offset) {
        this(sourceType, targetType, offset, offset == null ? null : ZoneOffset.of(offset));
    }

    /**
     * 基于ZoneOffset对象的构造函数，创建指定时区偏移量的转换器实例
     * <p>
     * 直接使用提供的ZoneOffset对象进行初始化。这是最直接的方式，适用于已经拥有
     * ZoneOffset对象的场景。
     *
     * @param sourceType 源数据类型的Class对象，用于类型检查和识别
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param zoneOffset ZoneOffset对象，指定转换时使用的时区偏移量
     * @throws NullPointerException 当zoneOffset参数为null时抛出（虽然方法本身不直接抛出，但内部逻辑会处理）
     */
    public AbstractWhenTypeConverter(Class<SourceType> sourceType, Class<TargetType> targetType, ZoneOffset zoneOffset) {
        this(sourceType, targetType, zoneOffset == null ? null : zoneOffset.getId(), zoneOffset);
    }

    /**
     * 私有的完整构造函数，用于初始化转换器的所有基本配置
     * <p>
     * 这是实际执行初始化工作的构造函数，其他公共构造函数最终都会调用此方法。
     * 它负责设置父类的配置参数并将ZoneOffset保存到当前实例中。
     *
     * @param sourceType 源数据类型的Class对象，用于类型检查和识别
     * @param targetType 目标数据类型的Class对象，用于类型检查和识别
     * @param offset     时区偏移量字符串，可能为null
     * @param zoneOffset ZoneOffset对象，可能为null
     */
    protected AbstractWhenTypeConverter(Class<SourceType> sourceType, Class<TargetType> targetType, String offset, ZoneOffset zoneOffset) {
        super(sourceType, targetType, offset, zoneOffset, zoneOffset == null ? null : zoneOffset.getId());
        this.zoneOffset = zoneOffset;
    }

    /**
     * 获取当前转换器使用的时区偏移量
     * <p>
     * 提供对外访问当前转换器时区偏移量的方法。返回值可能为null，
     * 表示该转换器未指定特定的时区偏移量。
     *
     * @return ZoneOffset对象，表示当前使用的时区偏移量；如果未指定则返回null
     */
    public ZoneOffset getZoneOffset() {
        return zoneOffset;
    }
}
