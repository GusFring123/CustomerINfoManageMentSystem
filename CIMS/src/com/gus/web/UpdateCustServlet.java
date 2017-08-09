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

@WebServlet(name = "UpdateCustServlet", urlPatterns = "/servlet/UpdateCustServlet")
public class UpdateCustServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
//        1.封装/校验数据
            Customer customer = new Customer();
            BeanUtils.populate(customer, req.getParameterMap());
//            单独处理preference
            StringBuffer buffer = new StringBuffer();
            String[] preferences = req.getParameterValues("preference");
            for (String preference:
                 preferences) {
                buffer.append(preference +",");
            }
            String preference = buffer.substring(0, buffer.length()-1);
            customer.setPreference(preference);
            customer.checkValues();
//        2.调用service方法进行更新数据
            service.updateCustomer(customer);
//        3.请求转发到listCustomerServlet，这样才能更新数据，转发到listCustomer.jsp是不对的
//            req.getRequestDispatcher("/listCustomer.jsp");
//            req.getRequestDispatcher(req.getContextPath()+"/servlet/ListCustomerServlet");
            resp.sendRedirect(req.getContextPath()+"/servlet/ListCustomerServlet");
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException(e);
        }

    }
}
