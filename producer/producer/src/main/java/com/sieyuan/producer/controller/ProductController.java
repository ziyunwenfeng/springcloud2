package com.sieyuan.producer.controller;

import com.sieyuan.producer.entity.Product;
import com.sieyuan.producer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;
    @GetMapping("/allProducts")
    public Product getAllProduct(){
        return productService.getAllProduct();
    }
    @PostMapping("/descProduct")
    public void descProduct(@RequestParam("id") int id,@RequestParam("num") int num){
        productService.descProduct(id,num);
    }
}
