package src;
public class Main {
    public static void main(String[] args) {
        System.out.println("Message Broker System Initialized");
        Broker broker= new Broker(1,"LocalBroker");
        QueuePartition partition1= new QueuePartition(1,"Partition-1");
        QueuePartition partition2= new QueuePartition(2,"Partition-2");
        QueuePartition partition3= new QueuePartition(3,"Partition-3");
        Topic topic1= new Topic(1,"Topic-1",partition1);
        Topic topic2= new Topic(2,"Topic-2",partition2);
        Topic topic3= new Topic(3,"Topic-3",partition3);
        broker.AddTopic(topic1);
        broker.AddTopic(topic2);
        broker.AddTopic(topic3);
        Producer producer1= new Producer(1,"Producer-1");
        Producer producer2= new Producer(2,"Producer-2");
        Producer producer3= new Producer(3,"Producer-3");
        Consumer consumer1= new Consumer(1,"Consumer-1");
        Consumer consumer2= new Consumer(2,"Consumer-2");
        Consumer consumer3= new Consumer(3,"Consumer-3");
        ConsumerGroup consumerGroup1= new ConsumerGroup(1,"ConsumerGroup-1");
        ConsumerGroup consumerGroup2= new ConsumerGroup(2,"ConsumerGroup-2");
        consumer1.addConsumerGroup(consumerGroup1);
        consumer2.addConsumerGroup(consumerGroup1);
        consumer3.addConsumerGroup(consumerGroup2);
        consumerGroup1.addConsumer(consumer1);
        consumerGroup1.addConsumer(consumer2);
        consumerGroup2.addConsumer(consumer3);
        consumerGroup1.addTopic(topic3);
        consumerGroup2.addTopic(topic2);
        
        broker.publish(producer3, topic3, 300);
        broker.publish(producer2, topic2, 200);
        broker.publish(producer1, topic1, 100);
        broker.consume(consumer1, topic3);
        broker.consume(consumer2, topic3);
        // for(Consumer consumer:consumerGroup1.getConsumers()){
        //     System.out.println(consumer);
        // }
    }
}
