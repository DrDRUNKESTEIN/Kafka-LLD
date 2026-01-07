package java;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Broker {
    private Integer broker_id;

    // composite key map: (topicId, consumerId) -> Partition
    private final ConcurrentMap<TopicConsumerKey, Partition> topicConsumerPartitionMap = new ConcurrentHashMap<>();

    public Broker(Integer broker_id){
        this.broker_id=broker_id;
    }

    public Integer publish_message(Topic topic, byte[] text){
        //We need to choose to what partition we want to write the message
        //this method uses a round robin strategy

        Integer previous_used_partition_in_topic=topic.getLast_used_partition();
        Integer partition_to_publish_to_in_topic=previous_used_partition_in_topic+1;
        Partition target_partition=topic.get_partiton_by_index(partition_to_publish_to_in_topic);
        Integer offset_in_partition=target_partition.get_offset_in_partition();
        long current_time=System.currentTimeMillis();
        Message message_to_publish=new Message(text, offset_in_partition,current_time);
        return topic.publish_to_partition(message_to_publish, partition_to_publish_to_in_topic);
    }

    
    public void assignPartitionToConsumer(Topic topic, Consumer consumer, Partition partition) {
        TopicConsumerKey key = new TopicConsumerKey(topic.getTopicId(), consumer.getConsumerId());
        this.topicConsumerPartitionMap.put(key, partition);
    }

    
    public Partition getPartitionForConsumer(Topic topic, Consumer consumer) {
        TopicConsumerKey key = new TopicConsumerKey(topic.getTopicId(), consumer.getConsumerId());
        return this.topicConsumerPartitionMap.get(key);
    }

    
    public void removePartitionAssignment(Topic topic, Consumer consumer) {
        TopicConsumerKey key = new TopicConsumerKey(topic.getTopicId(), consumer.getConsumerId());
        this.topicConsumerPartitionMap.remove(key);
    }
}
