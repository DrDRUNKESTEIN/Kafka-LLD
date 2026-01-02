package src;

import java.util.List;

public class ConsumerGroup {
    private Integer group_id;
    private String group_name;
    private List<Consumer> consumers;
    private List<Topic> topics;
    public ConsumerGroup(Integer group_id, String group_name){
        this.group_id = group_id;
        this.group_name = group_name;
        consumers = new java.util.ArrayList<Consumer>();
        topics = new java.util.ArrayList<Topic>();
    }
    public List<Topic> getTopics() {
        return topics;
    }
    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }
    public List<Consumer> getConsumers() {
        return consumers;
    }
    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }
    public void addConsumer(Consumer consumer){
        this.consumers.add(consumer);
    }
    public void deleteConsumer(Consumer consumer){
        this.consumers.remove(consumer);
    }
    public Integer getGroup_id() {
        return group_id;
    }
    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }
    public String getGroup_name() {
        return group_name;
    }
    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
    public void addTopic(Topic topic){
        this.topics.add(topic);
    }
    public void deleteTopic(Topic topic){
        this.topics.remove(topic);
    }
}
