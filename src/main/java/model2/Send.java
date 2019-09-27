package model2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import util.ConnectionUtil;

/**
 * work模式
 * 发布消息
 */
public class Send {

    private final static String QUEUE_NAME="test_02";

    public static void main(String[] args) throws  Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发布100条消息
        for (int i =0 ;i<100;i++){
            String message = ""+i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" [x]send :'"+message+"'");
            Thread.sleep(i*10);
        }
        //关闭通道，连接
        channel.close();
        connection.close();
    }
}
