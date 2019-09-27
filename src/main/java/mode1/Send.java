package mode1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * 简单队列模式
 * 生产者发布消息到队列
 *
 */
public class Send {

    private final  static String QUEUE_NAME = "test_01";

    public static void main(String[] args) throws  Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发布消息
        String message = "Hello world";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("[x]Sent'"+message+"'");
        //关闭通道，关闭连接
        channel.close();
        connection.close();
    }
}
