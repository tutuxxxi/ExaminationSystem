package com.xxx.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        //在作用域中存入值，方便各个jsp调用

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("url", servletContext.getContextPath());

    }
}
