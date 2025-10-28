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

- **基础类型转换**
    - 支持常见的基本数据类型之间的转换，如 `int`、`long`、`float`、`double`、`boolean` 等
    - 内置了布尔型、字符型、数字型与各基本类型间的转换器，例如 `BooleanToCharacterTypeConverter`、`NumberToShortTypeConverter` 等

- **字符串转换**
    - 提供字符串与其他类型的双向转换功能
    - 支持字符串到基本数据类型的转换，如 `StringToBooleanTypeConverter`、`StringToIntegerTypeConverter` 等
    - 支持对象到字符串的转换，并可选择大小写格式，如 `ObjectToStringTypeConverter`

- **时间和日期类型支持**
    - 支持多种时间日期类型的转换，包括 `LocalDateTime`、`Instant`、`Date`、`ZonedDateTime`、`OffsetDateTime` 等
    - 提供带时区偏移量的时间转换器，如 `LocalDateTimeToLongTypeConverter`、`InstantToZonedDateTimeTypeConverter`
    - 支持不同时区偏移量（-16到+16）的日期时间转换器

- **数据库结果集转换**
    - 支持 JDBC `ResultSet` 到数组和映射表的转换
    - 提供 `ResultSetToArrayTableTypeConverter` 和 `ResultSetToMapTableTypeConverter` 转换器

- **数字类型高精度转换**
    - 支持数字类型到 `BigDecimal` 和 `BigInteger` 的转换
    - 提供 `NumberToBigDecimalTypeConverter`、`StringToBigIntegerTypeConverter` 等转换器

- **自定义转换器**
    - 允许用户注册自定义类型转换器以满足特殊需求
    - 通过 `TypeConverterManager.register` 方法注册新的转换器

- **空值安全**
    - 内置空值处理机制，避免 `NullPointerException`
    - 在转换过程中对空值进行适当处理

- **高性能**
    - 采用缓存机制优化重复转换操作的性能
    - 使用 `ConcurrentHashMap` 和 `CopyOnWriteArrayList` 等并发容器提高性能

- **易于集成**
    - 简单的 API 设计，可快速集成到现有项目中
    - 提供默认的 `TypeConverterManager.DEFAULT` 实例，预注册了所有内置转换器

- **扩展性强**
    - 支持添加新的类型转换规则而无需修改核心代码
    - 通过继承 `TypeConverter` 抽象类可轻松实现自定义转换器

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
