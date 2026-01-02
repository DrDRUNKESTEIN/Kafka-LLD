package src;

import java.util.ArrayList;
import java.util.List;

public class Broker {
    private Integer id;
    private String name;
    private List<Topic> topics;
    public List<Topic> getTopics() {
        return topics;
    }
    public Broker(Integer id, String name){
        this.id = id;
        this.name = name;
        this.topics = new ArrayList<Topic>();
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void AddTopic(Topic topic){
        this.topics.add(topic);
    }
    public void RemoveTopic(Topic topic){
        this.topics.remove(topic);
    }
    public Topic GetTopicByName(String name){
        for(Topic topic : this.topics){
            if(topic.getName().equals(name)){
                return topic;
            }
        }
        return null;
    }
    public void consume(Consumer consumer, Topic topic){
        for(ConsumerGroup consumerGroup : consumer.getConsumerGroups()){
            if(consumerGroup.getTopics().contains(topic)){
                Integer value = topic.getPartition().GetValue();
                System.out.println("Consumer " + consumer.getName() + " consumed value: " + value + " from topic: " + topic.getName());
                return;
            }
        }
        System.out.println("Consumer " + consumer.getName() + " is not part of any consumer group subscribed to topic: " + topic.getName());
    }
    public void publish(Producer producer, Topic topic, Integer value){
        producer.produce(topic, value);
        System.out.println("Producer " + producer.getName() + " produced value: " + value + " to topic: " + topic.getName());
    }
}
