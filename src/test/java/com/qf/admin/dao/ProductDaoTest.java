package com.qf.admin.dao;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductDaoTest {
    @Test
    public void listProducts() throws Exception {
        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring-dao.xml");
        ProductDao productDao=ctx.getBean("productDaoImpl", ProductDao.class);
        System.out.println(productDao.listProducts().size());
    }
}