package model3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * 订阅模式
 *  向交换机中发布消息
 */
public class Send {

    private final static  String EXCHANGE_NAME="test_exchange_01";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发布消息
        String message = "hello world";
        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
        System.out.println("[x] Sent'"+message+"'");
        channel.close();
        connection.close();
    }
}

