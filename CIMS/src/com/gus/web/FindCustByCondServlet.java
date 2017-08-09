package com.gus.web;

import com.gus.domain.Customer;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet(name = "FindCustByCondServlet", urlPatterns = "/servlet/FindCustByCondServlet")
public class FindCustByCondServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取传入的参数信息
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
//        2.校验封装数据
        try {
            Customer customer = new Customer();
            BeanUtils.populate(customer, req.getParameterMap());
//        3.调用service中的方法查询，返回一个customer的list
            List<Customer> list = service.findCustByCond(customer);
//        4.存放到request域中
            req.setAttribute("list", list);
//        5.请求转发至listCustomer.jsp中作展示
            req.getRequestDispatcher("/listCustomer.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
}
