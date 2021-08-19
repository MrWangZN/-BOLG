# Spring的事务控制

## 1、 Spring的事务传播机制（Transaction Propagation）

```java
public enum Propagation {
    REQUIRED(0),
    SUPPORTS(1),
    MANDATORY(2),
    REQUIRES_NEW(3),
    NOT_SUPPORTED(4),
    NEVER(5),
    NESTED(6);
}
```

<font color='green' size = 5px>**支持当前事务的情况**</font>
**`REQUIRED`**：如果当前存在事务，则加⼊该事务；如果当前没有事务，则创建⼀个新的事务
**`SUPPORTS`**：如果当前存在事务，则加⼊该事务；如果当前没有事务，则以⾮事务的⽅式继续运⾏
**`MANDATORY`**：如果当前存在事务，则加⼊该事务；如果当前没有事务，**则抛出异常**。（mandatory：强制性)

<font color='green' size = 5px>**不⽀持当前事务的情况**</font>

**`REQUIRES_NEW`**：创建⼀个新的事务，如果当 前存在事务，则把当前事务挂起
**`NOT_SUPPORTED`**：以⾮事务⽅式运⾏，如果 当前存在事务，则把当前事务挂起
**`NEVER`**： 以⾮事务⽅式运⾏，如果当前存在事 务，**则抛出异常**

<font color='green' size = 5px>**其他情况**</font>

**NESTED(嵌套)**：使用具有多个可以回滚到的保存点的单个**物理事务**。这种部分回滚让内部事务作用域触发其作用域的回滚，而外部事务能够继续物理事务，尽管某些操作已经回滚。此设置通常映射到JDBC保存点（**目前还不是很了解**）

想要了解更多NESTED：http://www.iteye.com/topic/35907，里面有关于其解释和具体使用方法

## 2、Spring的隔离机制

```java
public enum Isolation {
    DEFAULT(-1),       //READ_COMMITTED
    READ_UNCOMMITTED(1),
    READ_COMMITTED(2),
    REPEATABLE_READ(4),
    SERIALIZABLE(8);
}
```

☆      和SQL 标准定义了四个隔离级别是一致的

**`READ_UNCOMMITTED`** ：最低的隔离级别，允许读取尚未提交的数据变更，可能会导致脏读、幻读或不可重复读
**`READ_COMMITTED`**：允许读取并发事务已经提交的 数据，可以阻⽌脏读，但是幻读或不可重复读仍有可能发⽣

**`REPEATABLE_READ`** ：对同⼀字段的多次读取结果 都是⼀致的，除⾮数据是被本身事务⾃⼰所修改，可以阻⽌脏读和不可重复读，但幻读仍有可能发⽣

**`SERIALIZABLE`**：序列化，最高的隔离级别，能够解决上述出现的所有问题，但是同样对性能的影响十分严重。

## 3、Transaction的回滚

**In its default configuration, the Spring Framework’s transaction infrastructure code marks a transaction for rollback only in the case of runtime, unchecked exceptions. That is, when the thrown exception is an instance or subclass of `RuntimeException`. ( `Error` instances also, by default, result in a rollback).**（<a href="https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#spring-data-tier">来自Spring文档 - 1.4.3</a>）

`在默认配置的情况下,spring在runtime,未检查异常时才会滚。默认当抛出的异常是RuntimeException及其子类的时候才会回滚（Error实例也会回滚）`

根据以上：我们需要配置**@Transactional(rollbackFor = Exception.class)**来保证在出现时候可以正确回滚。就如默认情况下,在发生IOException,并不会回滚。

![Alt text](https://www.runoob.com/wp-content/uploads/2013/12/exception-hierarchy.png)





## 4、Spring事务失效情况（大概有八种）

### 4.1、使用的数据库引擎不支持事务

- MySQL的两个主要搜索引擎InnoDB和MyIsam。InnoDB是支持事务的，而MyIsam是不支持事务的。

### 4.2、对象没有被Spring容器管理

```java
//缺少控制反转注入容器的注解   如：@service、@component
public class StudentService{
    
    @Transaction(...)
    public void changeNameById(int id ,String name){
        ...
    }
}
```

☆      上述例子中，对象没有被spring容器管理，而导致的事务失效。直接实例化对象的方式是无法实现事务的管理（需要AOP）

### 4.3、方法不是public修饰的

```tex
1.4.6节	
Method visibility and @Transactional

When you use transactional proxies with Spring’s standard configuration, you should apply the @Transactional annotation only to methods with public visibility. If you do annotate protected, private, or package-visible methods with the @Transactional annotation, no error is raised, but the annotated method does not exhibit the configured transactional settings. If you need to annotate non-public methods, consider the tip in the following paragraph for class-based proxies or consider using AspectJ compile-time or load-time weaving (described later).

When using @EnableTransactionManagement in a @Configuration class, protected or package-visible methods can also be made transactional for class-based proxies by registering a custom transactionAttributeSource bean like in the following example. Note, however, that transactional methods in interface-based proxies must always be public and defined in the proxied interface.

/**
 * Register a custom AnnotationTransactionAttributeSource with the
 * publicMethodsOnly flag set to false to enable support for
 * protected and package-private @Transactional methods in
 * class-based proxies.
 *
 * @see ProxyTransactionManagementConfiguration#transactionAttributeSource()
 */
@Bean
TransactionAttributeSource transactionAttributeSource() {
    return new AnnotationTransactionAttributeSource(false);
}

当你想要通过Spring的标准配置使用事务代理,你应该仅仅应该把@Transactional注解添加在方法可见性为public的。如果你把注解 @Transactional添加在protected...等修饰的方法上,虽然程序不会报错，但是事务的设置也不会生效。如果你确实需要在non-public methods上，请参考以下方式或者考虑使用ASpectJ来运行时或者编译时加强。

当使用 @EnableTransactionManagement 在一个@Configuration的类中,protected or package-visible methods同样可以进行事务但是需要是基于类的代理。如果是基于接口的代理方法必须是public和方法必须定义在接口interface中。
```



### 4.4、spring类中一个方法中调用另外一个需要事务的方法会导致这个是事务失效（自调用失效）

**原因：如下伪代码：事务的生效需要AOP动态代理的实现，而在method方法中，直接调用method2（）,方法的执行实际是this.method2()并没有经过AOP动态代理。**

```java
public void method(){//记住是一个方法调用另外一个需要事务的方法，导致这个需要事务的方法失效，而方法本身如果有事务其本身事务仍然有效
	method2();
}
@Transaction（....）
public void method2(){
	...//数据库需要事务的操作
}
```

**解决方法**

- 使用上下文容器获取对象
- 注入自己
- 讲方法抽取到另外一个对象中
- 使用编程式事务：大致方法如下

```java
@Autowired
TransactionTemplate transactionTemplate;

public void method(){
	
	transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    this.method2();
                } catch (Exception e) {
                    transactionStatus.setRollbackOnly();
                }
            }
     });
}

@Transaction（....）
public void method2(){
	...//数据库需要事务的操作
}
```

### 4.5、数据源没有配置事务管理器      (在spring Boot中不需要)

```java
@Bean
public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
}
```

### 4.6、事务的传播机制设置为不支持事务

如一下例子：在上面也介绍过了事务的传播机制中不支持事务的情况。

```java
public void method(){//记住是一个方法调用另外一个需要事务的方法，导致这个需要事务的方法失效，而方法本身如果有事务其本身事务仍然有效
	method2();
}

@Transactional(propagation = Propagation.NOT_SUPPORTED) //不支持事务
public void method2(){
	...
}
```

### 4.7、事务发生异常但是被俘获

```java
@Transactional 
public void method(){
	try{
    	...//事务代码
    }catch（Exception e）{
    	...//捕获异常
    }    
}
```

☆      在上面例子中，假设其他配置都正常，但是因为在事务的过程中发生了异常但是被捕获，无法上抛异常经过AOP感知代理回滚而导致事务失效

### 4.8、抛出的异常不在事务的处理范围4.

```
Transactional
public void method(){
  	...//事务代码
  	throw new Exception(...);
}
```

☆      在上面例子中,假设其他配置都正常，但是@Transactional是使用的是默认的配置，而@Transactional默认回滚是RuntimeException和其子类（上面也介绍过了）

☆      对代码进行修改

```java
@Transactional(rollbackFor = Exception.class) 
public void method(){
  	...//事务代码
  	throw new Exception(...);
}
```

### 总结

- 以上展示了8种事务失效的情况，然而通常是4.4、4.7、4.8所展示的情况
