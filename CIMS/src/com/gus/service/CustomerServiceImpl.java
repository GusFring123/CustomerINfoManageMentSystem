package com.gus.service;

import com.gus.dao.CustomerDao;
import com.gus.domain.Customer;
import com.gus.domain.Page;
import com.gus.exception.MsgException;
import com.gus.factory.BasicFactory;
import com.gus.util.DaoUtils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    CustomerDao dao = BasicFactory.getBasicFactory().getInstance(CustomerDao.class);

    @Override
    public void addCustom(Customer customer) throws MsgException {
//        1.检查用户名是否重复，如果重复则提示
        if (dao.findUserByName(customer.getName()) != null) {
            throw new MsgException("用户名已经存在");
        }
//        2.如果没有，则调用Dao中的方法添加用户
        dao.addCust(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return dao.findAllCustomer();
    }

    @Override
    public Customer getCustomerById(String id) {
        return dao.findCustomerById(id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        dao.updateCustomer(customer);
    }

    @Override
    public void delCustById(String id) throws MsgException {
        if (null == dao.findCustomerById(id)) {
            throw new MsgException("用户不存在");
        }
        dao.delCusyById(id);
    }

    @Override
    public void batchDelCust(String[] ids) {
        Connection connection = DaoUtils.getConnection();
        try {
            connection.setAutoCommit(false);
            for (String id : ids) {
                dao.delCustByIdWithTrans(connection, id);
            }
//            如果程序能走到这里，说明每个删除操作都已经完成，所以可一再这里进行提交事务
            DbUtils.commitAndCloseQuietly(connection);
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(connection);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
//如果要进行事务管理，必须获取connection

    @Override
    public List<Customer> findCustByCond(Customer customer) {
        return dao.findCustByCond(customer);
    }

    @Override
    public Page pageCust(int thispage, int rowperpage) {
        Page page = new Page();
//        当前页
        page.setThispage(thispage);
//        每页显示多少行
        page.setRowperpage(rowperpage);
//        总共多少行
        int countRow = dao.getCountRow();
        page.setCountrow(countRow);
//        总共多少页
        int countPage = countRow / rowperpage + (countRow % rowperpage == 0 ? 0 : 1);
        page.setCountpage(countPage);
//        首页
        page.setFirstpage(1);
//        尾页
        page.setLastpage(countPage);
//        上一页
        page.setPrepage(thispage == 1 ? thispage : thispage - 1);
//        下一页
        page.setNextpage(thispage == countPage ? thispage : thispage + 1);

        List<Customer> list = dao.getCusByPage((thispage-1)*rowperpage, rowperpage);

        page.setList(list);

        return page;
    }
}
