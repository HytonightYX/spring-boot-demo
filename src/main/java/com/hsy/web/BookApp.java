package com.hsy.web;

import com.hsy.domain.Book;
import com.hsy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HytonightYX
 * @date 2018/12/6 8:52
 */
@RestController
@RequestMapping("/api/v1")
public class BookApp {

    /**
     * 引入service层
     * 然后就可以在web层进行各种方法的定义了
     */
    @Autowired
    private BookService bookService;

    /**
     * 获取读书清单列表
     *
     * 处理:GET请求
     */

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }


    /**
     * 新增一条图书信息
     *
     * 处理: POST请求
     */
//    @PostMapping("/books")
//    public Book post(@RequestParam String name,
//                     @RequestParam String author,
//                     @RequestParam String description,
//                     @RequestParam int status) {
//        Book book = new Book();
//        book.setName(name);
//        book.setAuthor(author);
//        book.setDescription(description);
//        book.setStatus(status);
//
//        return bookService.save(book);
//    }

    //也可以像这样, spring自动将name, author等一系列
    //参数与实体对应, 直接保存
    @PostMapping("/books")
    public Book post(Book book) {
        return bookService.save(book);
    }

    /**
     * 获取一条书单信息
     *
     * 处理: GET请求 + {id}
     * @return
     */
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id) {
        return bookService.findOne(id);
    }

    /**
     * 更新一个书单
     *
     * 此时需要传入id
     *
     * 处理: PUT请求
     * postman请求中选择PUT请求, 并且Body类型要选择x-www-form....
     * @param id
     * @param name
     * @param author
     * @param description
     * @param status
     * @return
     */
    @PutMapping("/books/{id}")
    public Book updateOne(@RequestParam long id,
                          @RequestParam String name,
                          @RequestParam String author,
                          @RequestParam String description,
                          @RequestParam int status) {
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);

        return bookService.save(book);
    }

    /**
     * 删除一条书单
     *
     * 处理 DELETE请求
     */
    @DeleteMapping("/books/{id}")
    public void deleteOne(@PathVariable long id) {
        bookService.deleteOne(id);
    }

    /**
     * 根据作者姓名获取一个书单列表
     */
//    @PostMapping("/books/by")
//    public List<Book> findByAuthor(@RequestParam String author) {
//        return bookService.findByAuthor(author);
//    }

    /**
     * 根据作者姓名和阅读情况查询一个书单列表
     */
//    @PostMapping("/books/byAuthorAndStatus")
//    public List<Book> findByAuthorAndStatus(@RequestParam String author, @RequestParam int status) {
//        return bookService.findByAuthorAndStatus(author, status);
//    }

    /**
     * //根据描述结尾部分查询书单列表
     *
     *
     * //自定义更新
     *
     * @param description
     * @return
     */
    @PostMapping("/books/by")
    public int findBy(@RequestParam long id, @RequestParam int status, @RequestParam long uId) {
        //return bookService.findByDescriptionEndsWith(description);
        //return bookService.updateByJPQL(status, id)
        //return bookService.updateByJPQL(status, id);
//        return bookService.deleteByJPQL(id);
        return bookService.deleteAndUpdate(id, status, uId);
    }
}
