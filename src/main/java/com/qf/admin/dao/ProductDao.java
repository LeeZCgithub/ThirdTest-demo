package com.qf.admin.dao;

import com.qf.admin.pojo.po.Product;

import java.util.List;

public interface ProductDao {
    List<Product> listProducts();
    List<Product> listProductsByCid(String cid);

    List<Product> listProductByPage(int indexBegin,int pageSize);

    List<Product> listProductByCid(int indexBegin,int pageSize,String cid);
    //模糊查询
    List<Product> fuzzyQueryList(int indexBegin, int pageSize,String pname);
    List<Product> fuzzyQueryListCount(String pname);

    int deleteProductByPid(String pid);

    Product getProductByPid(String pid);

    void updateProduct(Product product);

    int saveProduct(Product product);


}
