package com.epam.rabbitmq.workqueue;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Worker {

	private final static String QUEUE_NAME = "work_queues";

	public static void main(String[] args) throws IOException, ShutdownSignalException, ConsumerCancelledException,
			InterruptedException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		boolean durable = true;
		channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		int prefetchCount = 1;
		channel.basicQos(prefetchCount);

		QueueingConsumer consumer = new QueueingConsumer(channel);
		boolean autoAck = false;
		channel.basicConsume(QUEUE_NAME, autoAck, consumer);

		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());

			System.out.println(" [x] Received '" + message + "'");
			doWork(message);
			boolean multiple = false;
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), multiple);
			System.out.println(" [x] Done");
		}
	}

	private static void doWork(String task) throws InterruptedException {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				Thread.sleep(1000);
			}
		}
	}

}
