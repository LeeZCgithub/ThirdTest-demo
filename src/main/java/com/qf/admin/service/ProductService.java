package com.qf.admin.service;

import com.qf.admin.pojo.po.Product;

import java.util.List;

public interface ProductService {
    int getPageNumber(int pageSize);
    List<Product> listProductByPage(int currentPage,int pageSize);

    int getPageNumerByCid(int pageSize,String cid);

    List<Product> listProductByCid(int currentPage,int pageSize,String cid);

    List<Product> fuzzyQueryList(int currentPage, int pageSize,String pname);
    int fuzzyQueryListCount(String pname,int pageSize);

    int deleteProductByPid(String pid);

    Product getProductByPid(String pid);

    void updateProduct(Product product);
    int saveProduct(Product product);

}
