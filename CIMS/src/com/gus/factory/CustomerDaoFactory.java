package com.gus.factory;

import com.gus.dao.CustomerDao;
import java.io.FileReader;
import java.util.Properties;

//单例模式，需要对外提供方法
//这个工厂提供了dao层和service的解耦
public class CustomerDaoFactory {
    private static CustomerDaoFactory customerDaoFactory = new CustomerDaoFactory();
    private static Properties properties = null;

    private CustomerDaoFactory() {

    }

    public static CustomerDaoFactory getCustomerFactory() {
        return customerDaoFactory;
    }


    //    读取配置文件
    static {
        properties = new Properties();
        try {
            properties.load(new FileReader(CustomerDaoFactory.class.getClassLoader().getResource("config.properties").getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public CustomerDao getCustomerDao() {
        String clazz = properties.getProperty("CustomerDao");
        try {
            return (CustomerDao) Class.forName(clazz).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object getInstance(String clazz) {
        clazz = properties.getProperty(clazz);
        try {
            return  Class.forName(clazz).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
