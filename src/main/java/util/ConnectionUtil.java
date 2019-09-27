package util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    /**
     * 获取MQ连接
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置服务地址
        connectionFactory.setHost("localhost");
        //设置端口
        connectionFactory.setPort(5672);
        //设置账号信息
        connectionFactory.setVirtualHost("testhost");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin123");
        //通过工厂获取连接
        Connection connection = connectionFactory.newConnection();
        return connection;
    }
}
