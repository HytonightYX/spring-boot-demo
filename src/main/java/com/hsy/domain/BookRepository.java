package com.hsy.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionContains(String des);

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


    /**
     * 自定义更新
     * 这里直接用update语句会报错, 因为hibernate不支持, 所以
     * 我们还要用一个注解 @Modifying
     */
    @Modifying
    @Transactional
    @Query("update Book b set b.status = ?1 where id = ?2")
    int updateByJPQL(int status, long id);

    /**
     * 自定义删除
     *
     * @param id
     * @return
     */
    @Modifying
    @Transactional
    @Query("delete Book b where b.id = ?1")
    int deleteByJPQL(long id);

    /**
     *
     */
}
