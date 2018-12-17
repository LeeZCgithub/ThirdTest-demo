package com.qf.admin.dao.impl;

import com.qf.admin.dao.ProductDao;
import com.qf.admin.pojo.po.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl extends JdbcDaoSupport implements ProductDao{

    @Autowired
    public void setJT(JdbcTemplate jdbcTemplate){
        super.setJdbcTemplate(jdbcTemplate);
    }
    @Override
    public List<Product> listProducts() {
        List<Product> list=getJdbcTemplate().query("select * from product",new BeanPropertyRowMapper<Product>(Product.class));

        return list;
    }

    @Override
    public List<Product> listProductsByCid(String cid) {
        List<Product> list=getJdbcTemplate().query("select * from product WHERE cid="+cid,new BeanPropertyRowMapper<Product>(Product.class));
        return list;
    }


    @Override
    public List<Product> listProductByPage(int indexBegin,int pageSize) {
        List<Product> list=getJdbcTemplate().query("SELECT * FROM product limit "+indexBegin+","+pageSize,new BeanPropertyRowMapper<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> listProductByCid(int indexBegin, int pageSize, String cid) {
        List<Product> list=getJdbcTemplate().query("select * from product where cid="+cid+" limit "+indexBegin+","+pageSize,new BeanPropertyRowMapper<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> fuzzyQueryList(int indexBegin, int pageSize,String pname) {
        List<Product> list=getJdbcTemplate().query("select * from product where pname like "+"'%"+pname+"%' limit "+indexBegin+","+pageSize,new BeanPropertyRowMapper<Product>(Product.class));
        return list;
    }

    @Override
    public List<Product> fuzzyQueryListCount(String pname) {
        List<Product> list=getJdbcTemplate().query("select * from product where pname like "+"'%"+pname+"%'",new BeanPropertyRowMapper<Product>(Product.class));
        return list;
    }

    @Override
    public int deleteProductByPid(String pid) {
        return getJdbcTemplate().update("delete from product where pid=?",pid);
    }

    @Override
    public Product getProductByPid(String pid) {
        String sql="select * from product where pid=?";
        return (Product) getJdbcTemplate().queryForObject(sql,new Object[]{pid}, new RowMapper<Object>() {
            @Override
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product =new Product();
                product.setPid(rs.getString("pid"));
                product.setCid(rs.getString("cid"));
                product.setHot(rs.getString("hot"));
                product.setPdate(rs.getDate("pdate"));
                product.setPdesc(rs.getString("pdesc"));
                product.setPimage(rs.getString("pimage"));
                product.setPflag(rs.getInt("pflag"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getDouble("price"));
                return product;

            }
        });
//        String sql = "select * from tb_user where id=? ";
//        return (UserInfo) getJdbcTemplate().queryForObject(sql, new Object[]{id}, new RowMapper<Object>() {
//            @Override
//            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
//                return mapRowHandler(resultSet);
//            }
//        });
    }

    @Override
    public void updateProduct(Product product) {
       getJdbcTemplate().update("update product set pname=?,price=?,pimage=?,pdesc=?,hot=?,pdate=? WHERE pid=?",product.getPname(),product.getPrice(),product.getPimage(),product.getPdesc(),product.getHot(),product.getPdate(),product.getPid());
    }

    @Override
    public int saveProduct(Product product) {
        return getJdbcTemplate().update("insert into product VALUES (?,?,?,?,?,?,?,?,?)",product.getPid(),product.getPname(),product.getPrice(),product.getPimage(),product.getPdesc(),product.getHot(),product.getPdate(),product.getPflag(),product.getCid());
    }


}
