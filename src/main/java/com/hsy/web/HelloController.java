package com.hsy.web;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器
 * RestController注解给HelloController被识别成为一个
 *
 * RequestMapping注解就是...那个东西啦
 *
 * @author HytonightYX
 */
//@Controller
@RestController
@RequestMapping("/v0")
public class HelloController {

//    @Autowired
//    private Book book;

    /**
     * RESTful API风格
     * value = "/say", method = RequestMethod.GET
     * 这样就表示限制请求的类型. 只有接受的请求为GET时候
     * 调到这个方法, 如果改成别的比如POST肯定会报错的
     */
//    @RequestMapping(value = "/say", method = RequestMethod.GET)
    @PostMapping("/say")
//    @GetMapping("/say")
    public String hello() {
        return "Hello Spring Boot!";
    }

    @GetMapping("/booksHTML")
    public String getHTML() {
        return "books";
    }

    /**getOne
     * 获取了一个json格式的对象
     * @ResponseBody
     * 作用：
     *       该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
     * 使用时机：
     *       返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；
     */
    @GetMapping("/bookstest")
    @ResponseBody
    public Object getTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "James");
        map.put("age", 18);
        return map;
    }

    /**
     * @PathVariable 注解的使用
     * 实现获取图书清单列表
     * 返回一个列表
     * 需求:nsername后面必须是小写字母和下划线
     * 正则表达式:{参数名:正则表达式}
     * @return book
     */
//    @GetMapping("/books/{id}+{username:[a-z_]+}")
//    public Object getOne(@PathVariable long id, @PathVariable String username) {
//        System.out.println("----- id: " + id + "-----");
//        System.out.println("username" + username);
//
//        Map<String, Object> book = new HashMap<>();
//        book.put("name", "三体");
//        book.put("isbn", "isbn-" + id);
//        book.put("author", "刘慈欣");
//        book.put("username", username);
//        return book;
//    }

    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id) {
        return null;
    }


    /**
     * 接收Form表单
     * @RequestParam("参数名") 同理, 不加括号则变量名和请求表单中的变量名一致
     * @return
     */

    public Object post(@RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String isbn) {

        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);

         // 在生产过程中可以加别的操作, 比如存储到数据库之类的

        return book;
    }


    /**
     *
     * 收到 http://localhost:8080/api/v1/books?page=1&size=0 请求
     * 会自动提取page = 1 和 size = 0, 并进行代码操作
     *
     * defaultValue = "10" 表示默认值, 类比python的可变参数
     *
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/books")
    @ResponseBody
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {

        Map<String, Object> book1 = new HashMap<>();
        book1.put("name", "三体");
        book1.put("isbn", "isbn-1");
        book1.put("author", "刘慈欣");

//        Map<String, Object> book2 = new HashMap<>();
//        book2.put("name", name);
//        book2.put("isbn", isbn);
//        book2.put("author", author);
//        book2.put("description", description);

        //模拟有两条书籍数据
        List<Map> contents = new ArrayList<>();
        contents.add(book1);
//        contents.add(book2);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("content", contents);

        return pageMap;
    }
}
