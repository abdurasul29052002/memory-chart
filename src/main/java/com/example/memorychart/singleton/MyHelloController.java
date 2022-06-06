package com.example.memorychart.singleton;

import com.example.memorychart.controller.HelloController;

public class MyHelloController {
    private static final MyHelloController myHelloController = new MyHelloController();
    private static HelloController helloController;

    private MyHelloController(){

    }

    public static MyHelloController getInstance(){
        if (helloController==null){
            helloController=new HelloController();
        }
        return myHelloController;
    }

    public HelloController getHelloController(){
        return helloController;
    }
}
