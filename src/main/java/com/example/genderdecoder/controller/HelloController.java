/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.genderdecoder.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    //Testing the HelloController 
    
    //Requesting the hello will print out Hello (test out, localhost:8080/hello)
    @RequestMapping("/hello")
    public String index() {
        return "Hello\n";
    }

}