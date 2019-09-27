package model4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 *  路由模式
 *  发布消息
 */
public class Send {

    private final static  String EXCHANGE_NAME="test_exchange_direct";

    public static void main(String[] args) throws  Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //发布消息到交换机
        String message = "删除功能";
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message.getBytes());
        System.out.println("Send = [" + message + "]");
        //关闭通道连接
        channel.close();
        connection.close();
    }
}
