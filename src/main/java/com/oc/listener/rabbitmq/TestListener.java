package com.oc.listener.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class TestListener implements ChannelAwareMessageListener {

    @Autowired
    private SimpleMessageConverter simpleMessageConverter;

    @Override
    public void onMessage(Message message, Channel channel) throws IOException {
        Object object = simpleMessageConverter.fromMessage(message);
        System.out.println(object.getClass());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

}
