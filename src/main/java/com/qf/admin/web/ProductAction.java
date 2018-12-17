package com.qf.admin.web;

import com.qf.admin.pojo.po.Product;
import com.qf.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
@Controller
@RequestMapping("/product")
public class ProductAction {
    /**
     *
     */
    //注入属性
    @Autowired
    private ProductService productService;
//    @GetMapping("/showProducts")
//    public String showAllProducts(Model model){
//        //1.调service获取List
//        List<Product> productList=productService.listProducts();
//        //2.存放到request范围中
//        model.addAttribute("productList",productList);
//        //3.默认转发操作
//        return "index";
//    }
    @GetMapping("/showProductsByPage")
    public String showProductsByPage(Model model,@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize){
        //1.调用service获取list
        List<Product> productList=productService.listProductByPage(currentPage,pageSize);
        //2.存放到request范围中
        model.addAttribute("productList",productList);
        //3.调用service获取pageNumber
        int pageNumber=productService.getPageNumber(pageSize);
        //4.放到request中
        model.addAttribute("pageNumber",pageNumber);
        //获得索引数减一
        int number=(currentPage-1)*pageSize;
        //放到request
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("number",number);
        //5.默认转发操作
        return "productsByPage";
    }
    @GetMapping("/showProductsByCid")
    public String showProductsByCid(Model model,@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize,String cid) {
        //1.调用service获取cid对应的List
        List<Product> productList = productService.listProductByCid(currentPage, pageSize, cid);
        //2.存放到request范围中
        model.addAttribute("productList", productList);
        //3.调用service获取pageNumber
        int pageNumber = productService.getPageNumerByCid(pageSize, cid);
        //4.放到request中
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("cid",cid);
        model.addAttribute("pageNumber", pageNumber);
        //获得索引数减一
        int number=(currentPage-1)*pageSize;
        //放到request
        model.addAttribute("number",number);
        //5.默认转发操作
        return "productsByPageByCid";
    }
    @GetMapping("/fuzzyQuery")
    public String fuzzyQuery(Model model,@RequestParam(defaultValue = "1")Integer currentPage, @RequestParam(defaultValue = "5")Integer pageSize,String pname){
        //1.调用service获取pname对应的List
        List<Product> productList=productService.fuzzyQueryList(currentPage,pageSize,pname);
        //2.存到request中
        model.addAttribute("productList",productList);
        //3.//获得pageNumber
        int pageNumber=productService.fuzzyQueryListCount(pname,pageSize);
        //放到request
        model.addAttribute("pageNumber",pageNumber);
        //获得索引数减一
        int number=(currentPage-1)*pageSize;
        //放到request
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("number",number);
        model.addAttribute("pname",pname);
        return "fuzzyQuery";
    }
    @GetMapping("/deleteProduct")
    public String deleteProduct(Model model,String pid,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize){

        int result=productService.deleteProductByPid(pid);
        //放到request
        model.addAttribute("result",result);
        //1.调用service获取list
        List<Product> productList=productService.listProductByPage(currentPage,pageSize);
        //2.存放到request范围中
        model.addAttribute("productList",productList);
        //3.调用service获取pageNumber
        int pageNumber=productService.getPageNumber(pageSize);
        //4.放到request中
        model.addAttribute("pageNumber",pageNumber);
        //获得索引数减一
        int number=(currentPage-1)*pageSize;
        //放到request
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        model.addAttribute("number",number);
        //5.默认转发操作
        return "productsByPage";
    }
    @GetMapping("/toUpdateProduct")
    public String toUpdateProduct(Model model,String pid,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize){
        Product product=productService.getProductByPid(pid);
        //加入到request
        model.addAttribute("product",product);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        //转发
        return "updateProduct";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(HttpServletRequest request,Product product,@RequestParam("pimage1") MultipartFile pimage1,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize) throws IOException {

        //如果文件不为空进行如下操作
        String filename=null;
        if (!pimage1.isEmpty()) {
            //获取路径 //images
            String path = request.getServletContext().getRealPath("/photo/1/");
            //获取原始图片的名称 //a.jpg
            filename = pimage1.getOriginalFilename();
            //获取File对象
            File filePath = new File(path, filename);
            //判断是否存在
            if (!filePath.getParentFile().exists()) {
                //意味着不存在
                filePath.getParentFile().mkdirs();
            }
            pimage1.transferTo(new File(path + File.separator + filename));
            String image="/photo/1/"+filename;
            product.setPimage(image);
        }

        String cid=product.getCid();
       productService.updateProduct(product);
        return "redirect:/product/showProductsByPage?currentPage="+currentPage+"&pageSize="+pageSize+"&cid="+cid;
    }
    @GetMapping("/toAddProduct")
    public String toAddProduct(){
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(HttpServletRequest request,Product product, @RequestParam("pimage1") MultipartFile pimage1,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize) throws IOException {
        //如果文件不为空进行如下操作
        String filename=null;
        if (!pimage1.isEmpty()) {
            //获取路径 //images
            String path = request.getServletContext().getRealPath("/photo/1/");
            //获取原始图片的名称 //a.jpg
            filename = pimage1.getOriginalFilename();
            //获取File对象
            File filePath = new File(path, filename);
            //判断是否存在
            if (!filePath.getParentFile().exists()) {
                //意味着不存在
                filePath.getParentFile().mkdirs();
            }
            pimage1.transferTo(new File(path + File.separator + filename));
        }
        System.out.println(pimage1);
        String image="/photo/1/"+filename;
        product.setPimage(image);
        String cid=product.getCid();
        int result=productService.saveProduct(product);
        return ("redirect:/product/showProductsByPage?currentPage="+currentPage+"&pageSize="+pageSize+"&cid="+cid);
    }


    //Cid更新
    @GetMapping("/toUpdateProductByCid")
    public String toUpdateProductByCid(Model model,String pid,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize){
        Product product=productService.getProductByPid(pid);
        //加入到request
        model.addAttribute("product",product);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pageSize",pageSize);
        //转发
        return "updateProductByCid";
    }
    @PostMapping("/updateProductByCid")
    public String updateProductByCid(HttpServletRequest request,Product product,@RequestParam("pimage1") MultipartFile pimage1,@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize) throws IOException {

        //如果文件不为空进行如下操作
        String filename=null;
        if (!pimage1.isEmpty()) {
            //获取路径 //images
            String path = request.getServletContext().getRealPath("/photo/1/");
            //获取原始图片的名称 //a.jpg
            filename = pimage1.getOriginalFilename();
            //获取File对象
            File filePath = new File(path, filename);
            //判断是否存在
            if (!filePath.getParentFile().exists()) {
                //意味着不存在
                filePath.getParentFile().mkdirs();
            }
            pimage1.transferTo(new File(path + File.separator + filename));
            String image="/photo/1/"+filename;
            product.setPimage(image);
        }
        String cid=product.getCid();
        productService.updateProduct(product);
        return "redirect:/product/showProductsByCid?currentPage="+currentPage+"&pageSize="+pageSize+"&cid="+cid;
    }
    @GetMapping("/deleteProductByCid")
    public String deleteProductByCid(Model model,String pid,@RequestParam(defaultValue = "1")Integer currentPage,@RequestParam(defaultValue = "10")Integer pageSize,String cid){

        int result=productService.deleteProductByPid(pid);
        //放到request
        model.addAttribute("result",result);

        //5.默认转发操作
        return "redirect:/product/showProductsByCid?currentPage="+currentPage+"&pageSize="+pageSize+"&cid="+cid;
    }
}
