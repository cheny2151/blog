package com.oc.listener.rabbitmq;

public class TestListener {

    public void handleRabbitMessage(String s){
        System.out.println("listener running...:"+s);
    }

}
