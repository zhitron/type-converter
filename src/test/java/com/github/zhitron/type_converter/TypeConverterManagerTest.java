package com.github.zhitron.type_converter;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.*;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author zhitron
 */
public class TypeConverterManagerTest {
    protected TypeConverterManager typeConverterManager = TypeConverterManager.DEFAULT;

    /**
     * 测试本地化语言环境类型转换功能
     */
    @Test
    public void testLocaleConversions() {
        // String转Locale
        Locale locale1 = typeConverterManager.converts("zh_CN", Locale.class, null);
        assertEquals("zh", locale1.getLanguage());
        assertEquals("CN", locale1.getCountry());

        // CharSequence转Locale
        Locale locale2 = typeConverterManager.converts(new StringBuilder("en_US"), Locale.class, null);
        assertEquals("en", locale2.getLanguage());
        assertEquals("US", locale2.getCountry());

        // Locale转String
        Locale locale3 = new Locale("fr", "FR");
        String localeStr = typeConverterManager.converts(locale3, String.class, null);
        assertEquals("fr-FR", localeStr);
    }

    /**
     * 测试CharSequence到数值类型的转换功能
     */
    @Test
    public void testCharSequenceToNumberTypes() {
        // CharSequence转BigDecimal
        assertEquals(new BigDecimal("123.45"),
                typeConverterManager.converts("123.45", BigDecimal.class, null));

        // CharSequence转BigInteger
        assertEquals(new BigInteger("12345"),
                typeConverterManager.converts("12345", BigInteger.class, null));

        // CharSequence转Byte
        assertEquals(Byte.valueOf((byte) 123),
                typeConverterManager.converts("123", Byte.class, null));

        // CharSequence转Double
        assertEquals(Double.valueOf(123.45),
                typeConverterManager.converts("123.45", Double.class, null));

        // CharSequence转Float
        assertEquals(Float.valueOf(123.45f),
                typeConverterManager.converts("123.45", Float.class, null));

        // CharSequence转Integer
        assertEquals(Integer.valueOf(123),
                typeConverterManager.converts("123", Integer.class, null));

        // CharSequence转Long
        assertEquals(Long.valueOf(123L),
                typeConverterManager.converts("123", Long.class, null));

        // CharSequence转Short
        assertEquals(Short.valueOf((short) 123),
                typeConverterManager.converts("123", Short.class, null));
        // 带千位分隔符的数值
        assertEquals(new BigDecimal("1234.56"),
                typeConverterManager.converts("1,234.56", Number.class, null));

        // 科学计数法
        assertEquals(new Double(1.23E4),
                typeConverterManager.converts("1.23E4", Double.class, null));

        // 负数
        assertEquals(Integer.valueOf(-123),
                typeConverterManager.converts("-123", Integer.class, null));

        // 带正号的数
        assertEquals(Integer.valueOf(123),
                typeConverterManager.converts("+123", Integer.class, null));

        // 小数转整数(截断)
        assertEquals(Double.valueOf(123.45),
                typeConverterManager.converts("123.45", Double.class, null));

        // Number转BigDecimal
        assertEquals(new BigDecimal("123"),
                typeConverterManager.converts(123, BigDecimal.class, null));

        // Number转BigInteger
        assertEquals(new BigInteger("123"),
                typeConverterManager.converts(123, BigInteger.class, null));
        // 无效的数值字符串
        assertNull(typeConverterManager.converts("invalid_number", Integer.class, null));

        // 空字符串转数值
        assertNull(typeConverterManager.converts("", Integer.class, null));

        // null输入处理
        assertNull(typeConverterManager.converts(null, Integer.class, null));

        // 使用默认值
        assertEquals(Integer.valueOf(0),
                typeConverterManager.converts("invalid", Integer.class, 0));
    }

    /**
     * 测试Boolean类型转换为其他类型的功能
     * 包括转换为数字类型、字符类型等
     */
    @Test
    public void testBooleanToOtherTypes() {
        // Boolean转字节型：true转为1，false转为0
        assertEquals(Byte.valueOf((byte) 1), typeConverterManager.converts(true, Byte.class, null));
        assertEquals(Byte.valueOf((byte) 0), typeConverterManager.converts(false, Byte.class, null));

        // Boolean转字符型：true转为'1'，false转为'0'
        assertEquals(Character.valueOf('1'), typeConverterManager.converts(true, Character.class, null));
        assertEquals(Character.valueOf('0'), typeConverterManager.converts(false, Character.class, null));

        // Boolean转双精度浮点型：true转为1.0，false转为0.0
        assertEquals(Double.valueOf(1.0), typeConverterManager.converts(true, Double.class, null));
        assertEquals(Double.valueOf(0.0), typeConverterManager.converts(false, Double.class, null));

        // Boolean转单精度浮点型：true转为1.0f，false转为0.0f
        assertEquals(Float.valueOf(1.0f), typeConverterManager.converts(true, Float.class, null));
        assertEquals(Float.valueOf(0.0f), typeConverterManager.converts(false, Float.class, null));

        // Boolean转整型：true转为1，false转为0
        assertEquals(Integer.valueOf(1), typeConverterManager.converts(true, Integer.class, null));
        assertEquals(Integer.valueOf(0), typeConverterManager.converts(false, Integer.class, null));

        // Boolean转长整型：true转为1L，false转为0L
        assertEquals(Long.valueOf(1L), typeConverterManager.converts(true, Long.class, null));
        assertEquals(Long.valueOf(0L), typeConverterManager.converts(false, Long.class, null));

        // Boolean转短整型：true转为1，false转为0
        assertEquals(Short.valueOf((short) 1), typeConverterManager.converts(true, Short.class, null));
        assertEquals(Short.valueOf((short) 0), typeConverterManager.converts(false, Short.class, null));
    }

    /**
     * 测试字符类型转换为其他类型的功能
     */
    @Test
    public void testCharacterToOtherTypes() {
        Character ch = 'A'; // ASCII码为65的字符

        // 字符转布尔型：非空字符转为true
        assertEquals(Boolean.TRUE, typeConverterManager.converts(ch, Boolean.class, null));
        // 字符转字节型：转为对应的ASCII码值
        assertEquals(Byte.valueOf((byte) 65), typeConverterManager.converts(ch, Byte.class, null));
        // 字符转双精度浮点型：转为对应的ASCII码值
        assertEquals(Double.valueOf(65.0), typeConverterManager.converts(ch, Double.class, null));
        // 字符转单精度浮点型：转为对应的ASCII码值
        assertEquals(Float.valueOf(65.0f), typeConverterManager.converts(ch, Float.class, null));
        // 字符转整型：转为对应的ASCII码值
        assertEquals(Integer.valueOf(65), typeConverterManager.converts(ch, Integer.class, null));
        // 字符转长整型：转为对应的ASCII码值
        assertEquals(Long.valueOf(65L), typeConverterManager.converts(ch, Long.class, null));
        // 字符转短整型：转为对应的ASCII码值
        assertEquals(Short.valueOf((short) 65), typeConverterManager.converts(ch, Short.class, null));
    }

    /**
     * 测试数字类型转换为其他类型的功能
     */
    @Test
    public void testNumberToOtherTypes() {
        Integer num = 1; // 测试用数字

        // 数字转布尔型：非零数字转为true
        assertEquals(Boolean.TRUE, typeConverterManager.converts(num, Boolean.class, null));
        // 数字转字节型：转为对应的字节值
        assertEquals(Byte.valueOf((byte) 1), typeConverterManager.converts(num, Byte.class, null));
        // 数字转字符型：转为对应的字符
        assertEquals(Character.valueOf((char) 1), typeConverterManager.converts(num, Character.class, null));
        // 数字转双精度浮点型：转为对应的浮点值
        assertEquals(Double.valueOf(1.0), typeConverterManager.converts(num, Double.class, null));
        // 数字转单精度浮点型：转为对应的浮点值
        assertEquals(Float.valueOf(1.0f), typeConverterManager.converts(num, Float.class, null));
        // 数字转长整型：转为对应的长整型值
        assertEquals(Long.valueOf(1L), typeConverterManager.converts(num, Long.class, null));
        // 数字转短整型：转为对应的短整型值
        assertEquals(Short.valueOf((short) 1), typeConverterManager.converts(num, Short.class, null));
    }

    /**
     * 测试字符串类型转换为其他类型的功能
     * 包括基本类型转换和特殊情况处理
     */
    @Test
    public void testStringToOtherTypes() {
        // 字符串转布尔型："true"转为true
        assertEquals(Boolean.TRUE, typeConverterManager.converts("true", Boolean.class, null));
        // 字符串转字节型："123"转为123
        assertEquals(Byte.valueOf((byte) 123), typeConverterManager.converts("123", Byte.class, null));
        // 字符串转字符型："A"转为'A'
        assertEquals(Character.valueOf('A'), typeConverterManager.converts("A", Character.class, null));
        // 字符串转双精度浮点型："123.45"转为123.45
        assertEquals(Double.valueOf(123.45), typeConverterManager.converts("123.45", Double.class, null));
        // 字符串转单精度浮点型："123.45"转为123.45f
        assertEquals(Float.valueOf(123.45f), typeConverterManager.converts("123.45", Float.class, null));
        // 字符串转整型："123"转为123
        assertEquals(Integer.valueOf(123), typeConverterManager.converts("123", Integer.class, null));
        // 字符串转长整型："123"转为123L
        assertEquals(Long.valueOf(123L), typeConverterManager.converts("123", Long.class, null));
        // 字符串转短整型："123"转为123
        assertEquals(Short.valueOf((short) 123), typeConverterManager.converts("123", Short.class, null));

        // 特殊字符串转换测试
        assertEquals(Integer.valueOf(123), typeConverterManager.converts("123", Integer.class, null));
        // 负数字符串转换
        assertEquals(Integer.valueOf(-456), typeConverterManager.converts("-456", Integer.class, null));
    }

    /**
     * 测试日期类型转换为其他类型的功能
     * 包括转换为时间戳、各种时间类型等
     */
    @Test
    public void testDateToOtherTypes() {
        Date date = new Date(1234567890000L); // 创建测试日期

        // Date转Instant：获取对应的Instant对象
        Instant instant = typeConverterManager.converts(date, Instant.class, null);
        assertNotNull(instant);
        assertEquals(date.toInstant(), instant);

        // Date转Long：获取对应的时间戳
        Long timestamp = typeConverterManager.converts(date, Long.class, null);
        assertNotNull(timestamp);
        assertEquals(Long.valueOf(date.getTime()), timestamp);

        // Date转LocalDateTime：指定时区"+8"
        LocalDateTime localDateTime = typeConverterManager.converts(date, LocalDateTime.class, null, "+8");
        assertNotNull(localDateTime);

        // Date转OffsetDateTime：指定时区"+8"
        OffsetDateTime offsetDateTime = typeConverterManager.converts(date, OffsetDateTime.class, null, "+8");
        assertNotNull(offsetDateTime);

        // Date转ZonedDateTime：指定时区"+8"
        ZonedDateTime zonedDateTime = typeConverterManager.converts(date, ZonedDateTime.class, null, "+8");
        assertNotNull(zonedDateTime);

        // Date转String：转换为字符串表示
        String result = typeConverterManager.converts(date, String.class, null);
        assertNotNull(result);
    }

    /**
     * 测试Instant类型转换为其他类型的功能
     */
    @Test
    public void testInstantToOtherTypes() {
        Instant instant = Instant.ofEpochMilli(1234567890000L); // 创建测试Instant

        // Instant转Date：转换为Date对象
        Date date = typeConverterManager.converts(instant, Date.class, null);
        assertNotNull(date);
        assertEquals(Date.from(instant), date);

        // Instant转Long：获取对应的时间戳
        Long timestamp = typeConverterManager.converts(instant, Long.class, null);
        assertNotNull(timestamp);
        assertEquals(Long.valueOf(instant.toEpochMilli()), timestamp);

        // Instant转LocalDateTime：指定时区"+8"
        LocalDateTime localDateTime = typeConverterManager.converts(instant, LocalDateTime.class, null, "+8");
        assertNotNull(localDateTime);

        // Instant转OffsetDateTime：指定时区"+8"
        OffsetDateTime offsetDateTime = typeConverterManager.converts(instant, OffsetDateTime.class, null, "+8");
        assertNotNull(offsetDateTime);

        // Instant转ZonedDateTime：指定时区"+8"
        ZonedDateTime zonedDateTime = typeConverterManager.converts(instant, ZonedDateTime.class, null, "+8");
        assertNotNull(zonedDateTime);
    }

    /**
     * 测试LocalDateTime类型转换为其他类型的功能
     */
    @Test
    public void testLocalDateTimeToOtherTypes() {
        LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 1, 12, 0, 0); // 创建测试LocalDateTime

        // LocalDateTime转Date：指定时区"+8"
        Date date = typeConverterManager.converts(localDateTime, Date.class, null, "+8");
        assertNotNull(date);

        // LocalDateTime转Instant：指定时区"+8"
        Instant instant = typeConverterManager.converts(localDateTime, Instant.class, null, "+8");
        assertNotNull(instant);

        // LocalDateTime转Long：指定时区"+8"
        Long timestamp = typeConverterManager.converts(localDateTime, Long.class, null, "+8");
        assertNotNull(timestamp);

        // LocalDateTime转OffsetDateTime：指定时区"+8"
        OffsetDateTime offsetDateTime = typeConverterManager.converts(localDateTime, OffsetDateTime.class, null, "+8");
        assertNotNull(offsetDateTime);

        // LocalDateTime转ZonedDateTime：指定时区"+8"
        ZonedDateTime zonedDateTime = typeConverterManager.converts(localDateTime, ZonedDateTime.class, null, "+8");
        assertNotNull(zonedDateTime);
    }

    /**
     * 测试OffsetDateTime类型转换为其他类型的功能
     */
    @Test
    public void testOffsetDateTimeToOtherTypes() {
        OffsetDateTime offsetDateTime = OffsetDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC); // 创建测试OffsetDateTime

        // OffsetDateTime转Date：转换为Date对象
        Date date = typeConverterManager.converts(offsetDateTime, Date.class, null);
        assertNotNull(date);

        // OffsetDateTime转Instant：获取对应的Instant对象
        Instant instant = typeConverterManager.converts(offsetDateTime, Instant.class, null);
        assertNotNull(instant);
        assertEquals(offsetDateTime.toInstant(), instant);

        // OffsetDateTime转LocalDateTime：转换为LocalDateTime对象
        LocalDateTime localDateTime = typeConverterManager.converts(offsetDateTime, LocalDateTime.class, null);
        assertNotNull(localDateTime);

        // OffsetDateTime转Long：获取对应的时间戳
        Long timestamp = typeConverterManager.converts(offsetDateTime, Long.class, null);
        assertNotNull(timestamp);
        assertEquals(Long.valueOf(offsetDateTime.toInstant().toEpochMilli()), timestamp);

        // OffsetDateTime转ZonedDateTime：转换为ZonedDateTime对象
        ZonedDateTime zonedDateTime = typeConverterManager.converts(offsetDateTime, ZonedDateTime.class, null);
        assertNotNull(zonedDateTime);
    }

    /**
     * 测试ZonedDateTime类型转换为其他类型的功能
     */
    @Test
    public void testZonedDateTimeToOtherTypes() {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC")); // 创建测试ZonedDateTime

        // ZonedDateTime转Date：转换为Date对象
        Date date = typeConverterManager.converts(zonedDateTime, Date.class, null);
        assertNotNull(date);

        // ZonedDateTime转Instant：获取对应的Instant对象
        Instant instant = typeConverterManager.converts(zonedDateTime, Instant.class, null);
        assertNotNull(instant);
        assertEquals(zonedDateTime.toInstant(), instant);

        // ZonedDateTime转LocalDateTime：转换为LocalDateTime对象
        LocalDateTime localDateTime = typeConverterManager.converts(zonedDateTime, LocalDateTime.class, null);
        assertNotNull(localDateTime);

        // ZonedDateTime转Long：获取对应的时间戳
        Long timestamp = typeConverterManager.converts(zonedDateTime, Long.class, null);
        assertNotNull(timestamp);
        assertEquals(Long.valueOf(zonedDateTime.toInstant().toEpochMilli()), timestamp);

        // ZonedDateTime转OffsetDateTime：转换为OffsetDateTime对象
        OffsetDateTime offsetDateTime = typeConverterManager.converts(zonedDateTime, OffsetDateTime.class, null);
        assertNotNull(offsetDateTime);
    }

    /**
     * 测试其他类型转换为String类型的功能
     */
    @Test
    public void testOtherTypesToString() {
        // 数字转String：123转为"123"
        assertEquals("123", typeConverterManager.converts(123, String.class, null));

        // 布尔值转String：true转为"true"，false转为"false"
        assertEquals("true", typeConverterManager.converts(true, String.class, null));
        assertEquals("false", typeConverterManager.converts(false, String.class, null));

        // 对象转String：直接返回对象的toString结果
        assertEquals("test", typeConverterManager.converts("test", String.class, null));
    }

    /**
     * 测试边界情况的处理功能
     * 包括null值处理和无效转换等
     */
    @Test
    public void testEdgeCases() {
        // Null值处理：null转String应返回null
        assertNull(typeConverterManager.converts(null, String.class, null));

        // 使用默认值：null转String，提供默认值"default"
        assertEquals("default", typeConverterManager.converts(null, String.class, "default"));
    }
}
