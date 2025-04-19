package com.example.ProjectA.controller;


import com.example.ProjectA.iService.iServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private iServiceProduct iServiceProduct;

    

}
