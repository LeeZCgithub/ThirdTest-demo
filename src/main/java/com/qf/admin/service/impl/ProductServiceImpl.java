package com.qf.admin.service.impl;

import com.qf.admin.dao.ProductDao;
import com.qf.admin.pojo.po.Product;
import com.qf.admin.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{
    //日志类多的定义
    private final static Logger logger= LoggerFactory.getLogger(ProductServiceImpl.class);
    //注入属性
    @Autowired
    private ProductDao productDao;

    public int getPageNumber(int pageSize){
        List<Product> products=null;
        int totalProduct=0;
        int pageNumber=0;
        try{
            //获取商品总件数
            products=productDao.listProducts();

            totalProduct=products.size();
            //获得总页数
            pageNumber=(int)Math.ceil(totalProduct*1.0/pageSize);
        }catch (Exception e){
           logger.info(e.getMessage(),e);
           e.printStackTrace();
        }
        return pageNumber;
    }

    @Override
    public List<Product> listProductByPage(int currentPage, int pageSize) {
        List<Product> products=null;
        int indexBegin;
        indexBegin=(int)(currentPage-1)*pageSize;
        try {
            products=productDao.listProductByPage(indexBegin,(int)pageSize);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int getPageNumerByCid(int pageSize,String cid) {
        List<Product> products=null;
        int totalProduct=0;
        int pageNumber=0;
        try {
            //根据cid获取商品总是
            products=productDao.listProductsByCid(cid);
            totalProduct=products.size();
            //获得总页数
            pageNumber=(int)Math.ceil(totalProduct*1.0/pageSize);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
        return pageNumber;
    }

    @Override
    public void updateProduct(Product product) {
         productDao.updateProduct(product);
    }

    @Override
    public List<Product> listProductByCid(int currentPage, int pageSize, String cid) {
        List<Product> products=null;
        int indexBegin;
        indexBegin=(int)(currentPage-1)*pageSize;
        try {
            products=productDao.listProductByCid(indexBegin,pageSize,cid);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int deleteProductByPid(String pid) {
        return productDao.deleteProductByPid(pid);
    }

    @Override
    public Product getProductByPid(String pid) {
        return productDao.getProductByPid(pid);
    }

    @Override
    public int saveProduct(Product product) {
        String pid= UUID.randomUUID().toString();
        product.setPid(pid);
        return productDao.saveProduct(product);
    }

    @Override
    public List<Product> fuzzyQueryList(int currentPage, int pageSize,String pname) {
        List<Product> products=null;
        int indexBegin;
        indexBegin=(int)(currentPage-1)*pageSize;
        try{
            products=productDao.fuzzyQueryList(indexBegin,pageSize,pname);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int fuzzyQueryListCount(String pname,int pageSize) {
        List<Product> products=null;
        int totalProduct=0;
        int pageNumber=0;
        try {
            //根据cid获取商品总是
            products=productDao.fuzzyQueryListCount(pname);
            totalProduct=products.size();
            //获得总页数
            pageNumber=(int)(totalProduct-1)/pageSize+1;
            //pageNumber=(int)Math.ceil(totalProduct*1.0/pageSize);
        }catch (Exception e){
            logger.info(e.getMessage(),e);
            e.printStackTrace();
        }
        return pageNumber;
    }
}
