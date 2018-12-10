package com.hsy.springbootdemo;

import com.hsy.domain.Book;
import com.hsy.domain.BookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {
    @Autowired
    private BookRepository bookRepository;

    /**
     * 生成几十条书单信息
     */
    @Test
    public void addBooks() {
        Book book = new Book();
        Random random = new Random();
        for (int i = 10; i <= 50; i++) {
            book.setId(i);
            book.setName("书目" + i);
            book.setStatus(random.nextInt(3));
            book.setDescription("书目" + i + "的描述");
            book.setAuthor("作者" + i);
            bookRepository.save(book);
            System.out.println(book);
        }
    }
}
