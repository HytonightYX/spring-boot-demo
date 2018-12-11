package com.hsy.web;

import com.hsy.domain.Book;
import com.hsy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author HytonightYX
 * @date 2018/12/8 22:21
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 获取书单列表
     *
     * 如下所示, 首先 @Controller 注解表明了
     * BookController 是一个Controller身份,
     * 随后, @GetMapping("/books")获取到请求,
     * 函数中看上去返回的是一个books字符串, 但是实际
     * 上不会以JSON格式返回"books", 而是会去templates
     * 下寻找books.html模板返回给游览器
     *
     * Model model是定义一个模型, 到时候会返回给模板这个模型
     * model.addAttribute("books", books); 就是说
     * 网books这个模板中添加books这个 List<Book>
     * @return
     */
    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }


    /**
     * 获取某书目的详情
     * @param id
     * @return
     */
    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model) {
        Book book;
        try {
            book = bookService.findOne(id);
        } catch (NoSuchElementException e) {
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }


    /**
     * 添加一条书单信息
     *
     * @return
     */
    @RequestMapping("/input")
    public String getBook(Model model) {
        return "input";
    }

}
