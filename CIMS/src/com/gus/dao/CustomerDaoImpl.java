package com.gus.dao;

import com.gus.domain.Customer;
import com.gus.domain.Page;
import com.gus.util.DaoUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public Customer findUserByName(String name) {
        String sql = "select * from customer where name = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanHandler<Customer>(Customer.class), name);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCust(Customer cust) {
        String sql = "insert into customer values (null,?,?,?,?,?,?,?,?)";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, cust.getName(), cust.getGender(), cust.getBirthday(), cust.getCellphone(), cust.getEmail(), cust.getPreference(), cust.getType(), cust.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

    @Override
    public List<Customer> findAllCustomer() {
        String sql = "select * from customer";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanListHandler<Customer>(Customer.class));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer findCustomerById(String id) {
        String sql = "select * from customer where  id= ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanHandler<Customer>(Customer.class), id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCustomer(Customer cust) {
        String sql = "update customer set name=? ,gender=?,birthday=?,cellphone=?,email=?,preference=?,type=?,description=? where id=?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, cust.getName(), cust.getGender(), cust.getBirthday(), cust.getCellphone(), cust.getEmail(), cust.getPreference(), cust.getType(), cust.getDescription(), cust.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delCusyById(String id) {
        String sql = "delete from customer where id = ?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            runner.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void delCustByIdWithTrans(Connection connection, String id) throws SQLException {
        String sql = "delete from customer where id = ?";
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql, id);

    }

    @Override
    public List<Customer> findCustByCond(Customer customer) {
        String sql = "select * from customer where 1=1 ";
        List<Object> list = new ArrayList<>();
        if (null != customer.getName() && !"".equals(customer.getName())) {
            sql += " and name like ?";
            list.add("%" + customer.getName() + "%");
        }
        if (null != customer.getGender() && !"".equals(customer.getGender())) {
            sql += " and gender = ?";
            list.add(customer.getGender());
        }
        if (null != customer.getType() && !"".equals(customer.getType())) {
            sql += " and type = ?";
            list.add(customer.getType());
        }
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            if (list.size() > 0) {
                return runner.query(sql, new BeanListHandler<Customer>(Customer.class), list.toArray());
            } else {
                return runner.query(sql, new BeanListHandler<Customer>(Customer.class));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getCountRow() {
        String sql = "select count(*) from customer";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return ((Long) runner.query(sql, new ScalarHandler())).intValue();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Customer> getCusByPage(int from, int count) {
        String sql = "select * from customer limit ?,?";
        try {
            QueryRunner runner = new QueryRunner(DaoUtils.getSource());
            return runner.query(sql, new BeanListHandler<Customer>(Customer.class), from, count);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}