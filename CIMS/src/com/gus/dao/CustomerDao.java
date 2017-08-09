package com.gus.dao;

import com.gus.domain.Customer;
import com.gus.domain.Page;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Customer dao.
 */
public interface CustomerDao {
    /**
     * Find user by name boolean.
     * 减产用户名是否已经存在
     *
     * @param name the name
     * @return the boolean
     */
    Customer findUserByName(String name);

    /**
     * Add cust.
     * 添加用户
     *
     * @param customer the customer
     */
    void addCust(Customer customer);

    /**
     * Find all customer list.
     * 查询所有的客户信息，返回所有客户信息组成的List，如果没有，则返回的集合为空
     *
     * @return the list
     */
    List<Customer> findAllCustomer();

    /**
     * Find customer by id customer.
     * 根据id查找用户，并返回
     *
     * @param id the id
     * @return the customer
     */
    Customer findCustomerById(String id);

    /**
     * Update customer by id.
     * 根据用户id与customer更新信息
     *
     * @param customer the customer
     */
    void updateCustomer(Customer customer);

    /**
     * Del cusy by id.
     * 根据id删除用户
     *
     * @param id the id
     */
    void delCusyById(String id);

    /**
     * 根据id删除客户,并管理事务
     *
     * @param connection the connection
     * @param id         要删除的id
     * @throws SQLException the sql exception
     */
    void delCustByIdWithTrans(Connection connection, String id) throws SQLException;

    /**
     * Find cust by cond list.
     * 条件查询客户信息
     *
     * @param customer 可以有用户名/性别/类型
     * @return the list 返回一个查询出来客户组成的集合
     */
    List<Customer> findCustByCond(Customer customer);


    /**
     * Gets count row.
     * 查询数据库一共有多少条记录
     *
     * @return the count row
     */
    int getCountRow();


    /**
     * Gets cus by page.
     * 查询指定记录后多少条记录
     * 所以前面传入的的应该是（thispage-1）*rowperpage
     * @param from  the from
     * @param count the count
     * @return the cus by page
     */
    List<Customer> getCusByPage(int from, int count);
}
