package com.gus.factory;

import com.gus.dao.CustomerDao;
import com.gus.domain.Customer;

import java.io.FileReader;
import java.util.Properties;

public class BasicFactory {
    private static BasicFactory basicFactory = new BasicFactory();
    private static Properties properties;
    private BasicFactory(){}

    public static BasicFactory getBasicFactory() {
        return basicFactory;
    }

    static {
        properties = new Properties();
        try {
            properties.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public <T> T getInstance(Class<T> clazz) {
        try {
            String cName = clazz.getSimpleName();
            String cImplName = properties.getProperty(cName);
            return (T)Class.forName(cImplName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
