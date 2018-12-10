#课堂笔记

##06节 URL路由-RESTful URL
####1 便于前后端分离的设计

一个url代表一个资源或者资源的转换url用名词, **不能出现动词**

**用不同的请求类型来描述操作!**

GET     .../books 获取读书清单列表

POST    .../books 新增一个清单

GET     .../books/{id} 获取一条读书清单

PUT     .../books/{id} 更新一个读书清单

DELETE  .../books/{id} 删除一个读书清单

####2 RequestMapping的简写形式
直接类型+Mapping即可

####3 Controller注解
就不是restful风格的返回值了


##08节 参数处理
####1 @PathVariable type var 获取参数

```java
    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {
        System.out.println("----- id: " + id + "-----");

        Map<String, Object> book = new HashMap<>();
        book.put("name", "三体");
        book.put("isbn", "isbn-32674823");
        book.put("author", "刘慈欣");
        return book;
    }
```

可以使用正则表达式过滤

```java
@GetMapping("/books/{id}+{username:[a-z_]+}")
```

####2 @RequestParam

需求:

显示图书列表

需要分页: 图书太多, 要分几页, 到时候请求包含具体哪一页

参数传递: ```/api/books?page=1&size=10```

如何接收呢?
```java
    @GetMapping("/books")
    @ResponseBody
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size)
```



####对比: @PathVariable @RequestParam
```
@PathVariable 用于 http://localhost:8080/eportal/orders?id=1001&name=XXX形式
@RequestParam 用于 http://localhost:8080/book/9783827319333 形式
```
附上一篇文章:Differences between @RequestParam and @PathVariable annotations in Spring MVC?
Read more: https://javarevisited.blogspot.com/2017/10/differences-between-requestparam-and-pathvariable-annotations-spring-mvc.html#ixzz5YrdAtQga




##10节 自定义配置
推荐yml文件来配置, JSON数据文件的超类,方便, 结构清晰
```
book:
  name: 互联网世界观
  author: 李善友
  isbn: ${random.uuid}
  description: ${book.name} ,这本书还不错哦.
```

@Value 来给pojo的属性注入值
```java
    @Value("${book.name}")
    private String name;

    @Value("${book.author}")
    private String author;

    @Value("${book.isbn}")
    private String isbn;

    @Value("${book.description}")
    private String description;
```

但是如果属性很多呢?这么写是不是太麻烦了?

有没有一种办法, 可以把它注入到实体呢

新建domain层
```
补充: 别人的帖子, 正确性未知, 暂且这么理解
1.domain：这一层是用来管理javaBean实体对象的, 里面存放的是与数据表一一对应的javaBean
2.dao:数据访问层，对数据库进行访问；
3.service：业务逻辑层，通过调用dao层来对数据库进行访问；
4.web：数据显示层；

补充, 14节老师提到了这个知识点:
一般分为 
数据库操作层(dao ?)
service层(写主要业务) 
Controller层(web层) 请求的接收以及返回响应?
```


##12节 自定义配置
自定义配置属性, 自行选择多环境yml


##13节 持久化层: JPA数据库操作
定义了关系映射以及实体对象持久化的标准接口**规范**, Hibernate实现了JPA的一个ORM的典型框架.

JPA是一套**接口规范**, 而不是一套ORM框架, 也不是一个产品

####Spring Data JPA
基于JPA进一步简化了DAO, 他实现了一种类似于声明式编程的方式. 开发者只需要编写数据访问接口(Repository), Spring Data
JPA就能基于接口中的方法命名自动地生成实现. (SQL语句)

Spring Data JPA是Spring基于ORM框架. JPA规范的基础上封装的一套JPA应用框架.


我们在Book上加了@Entity注解, 并且在yml中配置了create
```
  jpa:
    hibernate:
      ddl-auto: create
```
于是启动后自动映射到数据库, 并创建一张数据库表.

但是如果插入了数据, 使用create方式会重新创建一张表...原来的数据就么了...

所以我们也可以更换另一种更常用的方式 update, 如果没有表, 会新建一张, 如果有了, 就会update一次

## 13节 简单查询
这一讲多为实战, 见代码注释

特别地, 贴一个版本问题:
```java
/**
     * 获取一条书单信息
     *
     * @debug 1.X版本是可以直接使用findone（id）return 返回对象的，
     *         但2.0.0以后就需要findbyid（id).get(),或者getone（id）
     */
    public Book findOne(long id) {
        return bookRepository.findById(id).get();
    }
```

## 15节 复杂查询
比如仅仅通过一个作者姓名查询所有匹配书目
```java
/**
 * 继承JpaRepository, <>中怎么写还不是很清楚
 * 一个是类, 一个是类中的主键
 *
 * 自定义复杂的查询 : 写接口-> service中实现类的方法-> web层返回
 * 注意: 定义的方法名字非常讲究, jpa会根据你的方法名来自动生成SQL语句!
 * @author HytonightYX
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
}
```

附上相关官方文档, 以便随时查阅
https://docs.spring.io/spring-data/jpa/docs/2.1.1.RELEASE/reference/html/#jpa.query-methods


修BUG记录: 我们在 http://localhost:8080/api/v1/books/by 获取列表时, 应该在Postman中选择Body提交form-data或者x-www-urlencode !!!

---感谢 @李贺 学长的帮忙


## 16节 自定义查询
```java
/**
     * 自定义查询: 可以用于多表的查询, 比如两张表关联起来查询
     *
     *
     * 1 使用JPQL语句
     */
//    @Query("select b from Book b where length(b.name) > ?1")

    /**2 使用SQL语句
     * nativeQuery = true 表示可以用SQL语句直接查询
     */
    @Query(value = "select * from book where length (name)>?1", nativeQuery = true)
    List<Book> findByJPQL(int len);
```

## 17节 自定义更新
场景 : 根据某一个id来仅仅更新作者名字, 如果不去自定义, 采用默认方式, 不传值的
话会将其他字段覆盖为null, 我们要做的就是仅仅更改自定字段而不影响其他字段.

类似于下面这个SQL语句
```sql
update book set status=1 where id=20
```

实现代码:
```java
/**
     * 自定义更新
     */
    @Query("update Book b set b.status = ?1 where id = ?2")
    int updateByJPQL(int status, long id);
```

BUG:
```
javax.persistence.TransactionRequiredException: Executing an update/delete query
```

Solution:需要纳入事务管理, 在service 层加一个注解@Transactional
```java
/**
     * 自定义更新
     */
    @Transactional
    @Query("update Book b set b.status = ?1 where id = ?2")
    int updateByJPQL(int status, long id);
```

自定义删除的Repo
```java
    @Modifying
    @Query("delete Book b where b.id = ?1")
    int deleteByJPQL(long id);
```



##18节 [重要]JPA事物

比如有三个SQL语句a, b, c 要执行(先a后b最后c), `事务`能做到当其中任意一个操作出现故障,让三个语句不执行.提高项目的安全性

``遗留bug: 事务管理不生效``


##19节-20节 thymeleaf模板引擎 和 取值
使用方法如下, 在Controller中定义
```java
/**
     * 某书目的详情页面
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book = bookService.findOne(id);
        model.addAttribute("book", book);
        return "book";
    }
```
在templates文件夹下也要建立book.html文件, 使用th:text来加载某书目的详情
```html
<body>
    <div>
        <p>
            <strong>书名: </strong><span th:text="${book.name}">我们仨</span>
        </p>
        <p>
            <strong>作者: </strong><span th:text="${book.author}">杨绛</span>
        </p>
        <p>
            <strong>描述: </strong><span th:text="${book.description}">我是描述...</span>
        </p>
                  <p>
                      <strong>状态: </strong><span th:text="${book.status}">0</span>
                  </p>
              </div>
</body>
```

当然, 如果嫌每个p标签下都要写一遍${...}也可以这么干, 在body中定义这个类, 然后在下面使用*来代表这个类
```html
<body th:object="${book}">
    <div>
        <p>
            <strong>书名: </strong><span th:text="*{name}">我们仨</span>
        </p>
        <p>
            <strong>作者: </strong><span th:text="*{author}">杨绛</span>
        </p>
        <p>
            <strong>描述: </strong><span th:text="*{description}">我是描述...</span>
        </p>
        <p>
            <strong>状态: </strong><span th:text="*{status}">0</span>
        </p>
    </div>
</body>
```

##21节 thtymeleaf静态资源处理

需求: 需要给页面美化一下, 加点效果(使用bootstrap)

第一次加载启动访问http://127.0.0.1:8080/books/3之后没有效果???
1 静态资源要放在static文件夹下
2 html文件中需要引入
3 

```html
如果这么写其实是无效的
<link href="../static/css/bootstrap.css" rel="stylesheet">
和
<script src="../static/js/bootstrap.js"></script>

```


修改:
```html
<link th:href="@{/css/bootstrap.css}" rel="stylesheet">
和
<script th:src="@{/js/bootstrap.js}"></script>
```

但是这么写又发现bug, 本地打开会无效果...还是全部修改为cdn地址吧
```html
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
    
        <!-- Bootstrap -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
```


##22节 thymeleaf-判断
需求: 将网页上显示的状态(0 1 2)转换为对应的中文, 比如"0"转换为"未读"这样子

这个可以后台转换, 也可以前端转换, 这里使用tl模板的switch来转换
```html
<p th:switch="*{status}">
                <strong>状态: </strong>
                <span th:case="0">想读</span>
                <span th:case="1">在读</span>
                <span th:case="2">未读</span>
            </p>
```

新需求: 添加可视化标签
```
发现课程中老师写法的一个问题:
老师的html引入为:
<html xmlns:th="http://www.w3.org/1999/xhtml">
这应该是不对的, 正确的写法如下(来自thymeleaf官方文档)

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
```


##23节 迭代
需求: 在一个页面上显示整个书单的列表

使用表格
```html
<tr th:each="book,iterStat:${books}">
                <td th:text="${iterStat.count}">1</td>
                <td th:text="${book.name}">书名</td>
                <td th:text="${book.author}">作者</td>
                <td th:text="${book.description}">说明</td>
                <td th:switch="${book.status}" style="min-width: 60px">
                    <span th:case="0">未读</span>
                    <span th:case="1">在读</span>
                    <span th:case="2">已读</span>
                    <span th:case="*">未知</span>
                </td>
            </tr>
```
```
iterStat:
    count: 计数
    index: 从0开始的索引
    size:  最大索引
    even/odd: 是偶数/是奇数
    first/last: 第一条/ 最后一条
```


## 24节  thymeleaf URL
```html
替换
<!--<td><a th:text="${book.name}" th:href="@{/books/{id}(id=${book.id})}">书名</a></td>-->
                <td ><a href="#" th:text="${book.name}" th:href="@{'/books/'+${book.id}}"></a></td>
```

加一个返回按钮
```html
<!--添加返回按钮-->
        <a href="javascript:history.go(-1)" class="btn btn-default">返回</a>
```
