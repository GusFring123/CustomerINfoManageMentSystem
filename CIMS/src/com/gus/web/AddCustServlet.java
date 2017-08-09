package com.gus.web;

import com.gus.domain.Customer;
import com.gus.exception.MsgException;
import com.gus.factory.BasicFactory;
import com.gus.service.CustomerService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddCustServlet", urlPatterns = "/servlet/AddCustServlet")
public class AddCustServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         CustomerService service = BasicFactory.getBasicFactory().getInstance(CustomerService.class);

        //        1.校验数据，封装数据
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            Customer customer = new Customer();
            BeanUtils.populate(customer, req.getParameterMap());
//            在这里客户的爱好可能有多个，所以需要单独处理一下
            String[] preferences = req.getParameterValues("preference");
            StringBuffer buffer = new StringBuffer();
            for (String preference :
                    preferences) {
                buffer.append(preference + ",");
            }
//            切割Stringbuffer，因为最后还有一个逗号
            String preference = buffer.substring(0, buffer.length() - 1);
            customer.setPreference(preference);
            customer.checkValues();
//        2.调用service方法，添加客户信息
            service.addCustom(customer);
//        3.跳转到主页
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        } catch (MsgException e) {
            e.printStackTrace();
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("/addCust.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
