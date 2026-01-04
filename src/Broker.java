package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Broker {
    private Integer id;
    private String name;
    private List<Topic> topics;
    // Map to track, for each topic name, which offset is stored in which partition (location)
    // topicName -> (offset -> partitionName)
    private Map<String, Map<Integer, String>> topicOffsetLocationMap = new ConcurrentHashMap<>();

    
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
        Integer offset = producer.produce(topic, value);
        // record mapping topic -> offset -> partition name
        if (offset != null && offset >= 0) {
            this.recordOffsetLocation(topic.getName(), offset, topic.getPartition().getName());
        }
        System.out.println("Producer " + producer.getName() + " produced value: " + value + " to topic: " + topic.getName() + " offset: " + offset);
    }

    private void recordOffsetLocation(String topicName, Integer offset, String partitionName) {
        Map<Integer, String> offsets = this.topicOffsetLocationMap.computeIfAbsent(topicName, k -> new ConcurrentHashMap<>());
        offsets.put(offset, partitionName);
    }

    public String getLocationForOffset(String topicName, Integer offset) {
        Map<Integer, String> offsets = this.topicOffsetLocationMap.get(topicName);
        if (offsets == null) return null;
        return offsets.get(offset);
    }

    public Map<Integer, String> getAllOffsetsForTopic(String topicName) {
        return this.topicOffsetLocationMap.get(topicName);
    }
}
