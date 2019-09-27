package model3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import util.ConnectionUtil;

/**
 * 消费者2号
 */
public class Recev2 {
    private final static String EXCHANGE_NAME="test_exchange_01";
    private final static String QUEUE_NAME = "test_03_02";

    public static void main(String[] args) throws  Exception{
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        //创建通道
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //绑定队列到交换机
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        //同一时刻服务器只会发送一条消息给消费者
        channel.basicQos(1);

        //定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //监听队列，手动返回确认
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("Recev2 received [" + message + "]");
            Thread.sleep(10);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }

    }
}
