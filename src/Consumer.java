package src;

import java.util.List;

public class Consumer {
    private Integer id;
    private String name;
    private List<ConsumerGroup> consumerGroups;
    public List<ConsumerGroup> getConsumerGroups() {
        return consumerGroups;
    }
    public void setConsumerGroups(List<ConsumerGroup> consumerGroups) {
        this.consumerGroups = consumerGroups;
    }
    public void addConsumerGroup(ConsumerGroup consumerGroup){
        this.consumerGroups.add(consumerGroup);
    }
    public void deleteConsumerGroup(ConsumerGroup consumerGroup){
        this.consumerGroups.remove(consumerGroup);
    }
    public Consumer(Integer id, String name){
        this.id = id;
        this.name = name;
        this.consumerGroups = new java.util.ArrayList<ConsumerGroup>();
        
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
}
