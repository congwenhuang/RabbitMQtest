package model5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * 主题模式（通配符模式）
 */
public class Send {

    private final static String EXCHANGE_NAME="test_exchange_topic";

    public static void main(String[] args) throws Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");
        //发布消息
        String message = "hello world!";
        channel.basicPublish(EXCHANGE_NAME,"routekey.1",null,message.getBytes());
        System.out.println("Send = [" + message + "]");
        //关闭通道连接
        channel.close();
        connection.close();
    }
}
