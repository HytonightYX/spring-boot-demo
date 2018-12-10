package com.hsy.service;

import com.hsy.domain.Book;
import com.hsy.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Service 表示这是service层
 *
 * @author HytonightYX
 * @date 2018/12/6 8:48
 */

@Service
public class BookService {


    /**
     * 注入 BookRepository
     */
    @Autowired
    private BookRepository bookRepository;


    /**
     * 查询所有书单列表
     * @return
     */
    public List<Book> findAll() {
        //bookRepository 已经自带findAll() 方法
        return bookRepository.findAll();
    }


    /**
     * 新增一条书单信息
     * @param book
     * @return
     */
    public Book save(Book book) {
        return bookRepository.save(book);
    }


    /**
     * 获取一条书单信息
     *
     * @debug 1.X版本是可以直接使用findone（id）return 返回对象的，
     *         但2.0.0以后就需要findbyid（id).get(),或者getone（id）
     */
    public Book findOne(long id) {
        return bookRepository.findById(id).get();
    }

    /**
     * 删除一条书单信息
     */
    public void deleteOne(long id) {
        bookRepository.deleteById(id);
    }

    /**
     * 根据author查询书单列表
     * @param author
     * @return
     */
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    /**
     * 根据 author 和 status 查询一个书单列表
     * @param author
     * @param status
     * @return
     */
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    /**
     * 查询以des结尾的书籍描述 description
     * @param des
     * @return
     */
    public List<Book> findByDescriptionEndsWith(String des) {
        return bookRepository.findByDescriptionContains(des);
    }


    /**
     * 自定义查询
     * @param len
     * @return
     */
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }


    /**
     * 自定义更新
     */
    @Transactional
    public int updateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    /**
     * 自定义删除操作
     * 根据id 删除数据
     * @param id
     * @return
     */
    @Transactional
    public int deleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }

    /**
     * 定义一个方法, 要删除一条记录, 并且新增一条记录
     *
     * 知识点: 事务操作方法
     */
    @Transactional
    public int deleteAndUpdate(long id, int status, long uId) {
        int dcount = bookRepository.deleteByJPQL(id);
        int ucount = bookRepository.updateByJPQL(status, uId);
        return dcount + ucount;
    }
}
