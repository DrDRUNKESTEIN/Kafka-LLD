package java;

public class Consumer {
    private Integer consumer_id;
    private ConsumerGroup consumer_group;
    public Broker broker;
    
    /**
     * Consume the message at the given offset for the provided topic.
     * If this consumer has an assigned partition for the topic in the broker, read from that partition.
     * Otherwise fall back to the topic's default partition 0.
     * Returns null if message not found.
     */
    public byte[] consume_message_at_offset(Topic topic, Integer offset){
        if (topic == null || offset == null) return null;
        Partition p = null;
        if (this.broker != null) {
            p = this.broker.getPartitionForConsumer(topic, this);
        }
        if (p == null) {
            // fallback to topic's partition 0 (or topic.get_partiton_by_index(0))
            p = topic.get_partiton_by_index(0);
        }
        return p.getMessageAtOffset(offset);
    }

    public Integer getConsumerId() {
        return consumer_id;
    }

    public ConsumerGroup getConsumerGroup() {
        return consumer_group;
    }
}
