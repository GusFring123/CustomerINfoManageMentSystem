package com.gus.service;

import com.gus.domain.Customer;
import com.gus.domain.Page;
import com.gus.exception.MsgException;

import java.sql.Connection;
import java.util.List;

/**
 * The interface Customer service.
 */
public interface CustomerService {
    /**
     * Add custom.添加用户
     *
     * @param customer the customer将客户信息封装到javabean
     * @throws MsgException the msg exception
     */
    void addCustom(Customer customer) throws MsgException;

    /**
     * Find all customer list.
     * 找到所有的用户，以list形式返回
     *
     * @return the list
     */
    List<Customer> getAllCustomer();

    /**
     * Gets customer by id.
     * 根据用户id获取用户
     *
     * @param id the id
     * @return the customer by id
     */
    Customer getCustomerById(String id);

    /**
     * Update customer.
     * 根据用户id与传入的Customer更新用户信息
//     * @param id the id
     */
    void updateCustomer(Customer customer);

    void delCustById(String id) throws MsgException;
    /**
     * 批量删除客户，其中会进行事务管理
     *
     * @param ids 所有要删除的id组成的数组
     */
    void batchDelCust(String[] ids);


    List<Customer> findCustByCond(Customer customer);

    /*
    * 分页查询
    * 参数是第几页以及每页显示的个数
    *
    * */
    Page pageCust(int thispage, int rowperpage);
}
