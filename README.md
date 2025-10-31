# Type Converter

## 📄 项目简介

`Type Converter` 是一个Java类型转换工具库，提供简单易用的类型转换功能，帮助开发者在不同数据类型间进行高效转换。

---

## 🚀 快速开始

### 构建要求

- JDK 8 或以上（推荐使用 JDK 8）
- Maven 3.x

### 添加依赖

你可以通过 Maven 引入该项目：

```xml

<dependency>
    <groupId>io.github.zhitron</groupId>
    <artifactId>type-converter</artifactId>
    <version>1.1.0</version>
</dependency>
```

---

## 🧩 功能特性

### 1. 基础类型转换

Type Converter 提供了丰富的基础数据类型之间的转换功能：

- **布尔型转换**：支持 `Boolean` 与其他基本类型（`Byte`、`Character`、`Double`、`Float`、`Integer`、`Long`、`Short`）之间的相互转换
- **字符型转换**：支持 `Character` 与数值类型及布尔型的转换
- **数值型转换**：支持各种数值类型（`Byte`、`Short`、`Integer`、`Long`、`Float`、`Double`）之间的转换，包括溢出检查

```java
// 示例：布尔型到数值型转换
Integer intValue = TypeConverterManager.DEFAULT.converts(true, Integer.class, 0);
// intValue = 1

// 示例：字符到数值转换
Integer charToInt = TypeConverterManager.DEFAULT.converts('A', Integer.class, 0);
// charToInt = 65 (ASCII码)
```


### 2. 字符串转换

提供了全面的字符串与其他类型之间的双向转换功能：

- **字符串到基本类型**：支持字符串到布尔型、数值型、字符型的转换
- **对象到字符串**：支持任意对象到字符串的转换，可选择大小写格式
- **字符串到字符**：要求字符串长度必须为1

```java
// 示例：字符串到数值转换
String str = "123";
Integer num = TypeConverterManager.DEFAULT.converts(str, Integer.class, 0);
// num = 123

// 示例：对象到字符串转换（大小写控制）
Object obj = "hello";
String upperStr = TypeConverterManager.DEFAULT.converts(obj, String.class, "", "upper");
// upperStr = "HELLO"
```


### 3. 时间和日期类型支持

支持多种时间日期类型的转换，包括时区处理：

- **时间类型**：支持 `LocalDateTime`、`Instant`、`Date`、`ZonedDateTime`、`OffsetDateTime` 等类型之间的相互转换
- **时区支持**：提供带时区偏移量的时间转换器，支持-16到+16的时区偏移量
- **抽象基类**：提供 `AbstractWhenTypeConverter` 抽象基类，便于实现时区相关的转换器

```java
// 示例：LocalDateTime到Instant转换（指定时区）
LocalDateTime localDateTime = LocalDateTime.now();
Instant instant = TypeConverterManager.DEFAULT.converts(
    localDateTime, 
    Instant.class, 
    null, 
    "+8"
);
```


### 4. 数据库结果集转换

支持 JDBC `ResultSet` 到数组和映射表的转换：

- **数组转换**：`ResultSetToArrayTableTypeConverter` 将结果集转换为二维数组
- **映射表转换**：`ResultSetToMapTableTypeConverter` 将结果集转换为 Map 数组
- **列处理**：自动处理列索引、列名和列标签，支持大小写转换

```java
// 示例：ResultSet到Map数组转换
ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
Map<String, Object>[] result = TypeConverterManager.DEFAULT.converts(
    resultSet, 
    Map[].class, 
    new HashMap[0]
);
```


### 5. 数字类型高精度转换

支持高精度数值类型的转换：

- **BigDecimal/BigInteger**：支持数字类型到 `BigDecimal` 和 `BigInteger` 的转换
- **字符串到高精度数**：支持 `CharSequence` 到高精度数值的转换
- **特殊格式支持**：支持千分位分隔符处理和科学计数法

```java
// 示例：字符串到BigDecimal转换
String bigDecimalStr = "123456.789";
BigDecimal bd = TypeConverterManager.DEFAULT.converts(bigDecimalStr, BigDecimal.class, BigDecimal.ZERO);
```


### 6. 本地化语言环境支持

支持本地化语言环境的转换：

- **字符串到Locale**：支持 `CharSequence` 和 `String` 到 `Locale` 对象的转换
- **Locale到字符串**：支持 `Locale` 对象到字符串的反向转换
- **格式支持**：支持标准语言环境格式（如 "zh-CN"、"en-US"）的解析和转换

```java
// 示例：字符串到Locale转换
String localeStr = "zh-CN";
Locale locale = TypeConverterManager.DEFAULT.converts(localeStr, Locale.class, Locale.getDefault());
```


### 7. 自定义转换器

允许用户注册自定义类型转换器：

- **注册机制**：通过 `TypeConverterManager.register` 方法注册新的转换器
- **基类支持**：提供 `TypeConverter` 抽象类作为自定义转换器的基类
- **灵活扩展**：支持添加新的类型转换规则而无需修改核心代码

```java
// 示例：自定义转换器
public class CustomTypeConverter extends TypeConverter<SourceType, TargetType> {
    // 实现转换逻辑
}
static {
// 注册自定义转换器
    TypeConverterManager.DEFAULT.register(new CustomTypeConverter());
}
```


### 8. 空值安全

内置空值处理机制，避免 `NullPointerException`：

- **空值处理**：在转换过程中对空值进行适当处理
- **默认值支持**：支持在转换失败时返回默认值

### 9. 高性能

采用多种优化策略提高性能：

- **缓存机制**：采用缓存机制优化重复转换操作的性能
- **并发容器**：使用 `ConcurrentHashMap` 和 `CopyOnWriteArrayList` 等并发容器提高性能
- **双重查找机制**：提供精确类型匹配和通用转换器两种查找机制

### 10. 易于集成

提供简单易用的API设计：

- **默认实例**：提供默认的 `TypeConverterManager.DEFAULT` 实例，预注册了所有内置转换器
- **链式调用**：支持方法链式调用
- **简洁API**：API设计简洁明了，易于集成到现有项目中

### 11. 扩展性强

支持灵活的扩展机制：

- **插件化设计**：支持添加新的类型转换规则而无需修改核心代码
- **继承支持**：通过继承 `TypeConverter` 抽象类可轻松实现自定义转换器

### 12. 完善的异常处理

提供专门的异常处理机制：

- **专用异常**：提供专门的 `TypeConverterException` 异常类型用于处理转换过程中的各类错误

---

## ✍️ 开发者

- **Zhitron**
- 邮箱: zhitron@foxmail.com
- 组织: [Zhitron](https://github.com/zhitron)

---

## 📦 发布状态

当前版本：`1.0.0`

该项目已发布至 [Maven Central](https://search.maven.org/)，支持快照版本与正式版本部署。

---

## 🛠 源码管理

GitHub 地址：https://github.com/zhitron/type-converter

使用 Git 进行版本控制：

```bash
git clone https://github.com/zhitron/type-converter.git
```

---

## 📚 文档与社区

- Javadoc 文档可通过 `mvn javadoc:javadoc` 生成。
- 如有问题或贡献，请提交 Issues 或 PR 至 GitHub 仓库。

---

## 📎 License

Apache License, Version 2.0  
详见 [LICENSE](https://www.apache.org/licenses/LICENSE-2.0.txt)

---
