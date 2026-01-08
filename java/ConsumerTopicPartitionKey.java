package java;

import java.util.Objects;

/**
 * Composite key for (topicId, consumerId, partitionIndex)
 */
public final class ConsumerTopicPartitionKey {
    private final Integer topicId;
    private final Integer consumerId;
    private final Integer partitionIndex;

    public ConsumerTopicPartitionKey(Integer topicId, Integer consumerId, Integer partitionIndex) {
        this.topicId = topicId;
        this.consumerId = consumerId;
        this.partitionIndex = partitionIndex;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    public Integer getPartitionIndex() {
        return partitionIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsumerTopicPartitionKey that = (ConsumerTopicPartitionKey) o;
        return Objects.equals(topicId, that.topicId) && Objects.equals(consumerId, that.consumerId) && Objects.equals(partitionIndex, that.partitionIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, consumerId, partitionIndex);
    }

    @Override
    public String toString() {
        return "CTPKey{" + topicId + "," + consumerId + "," + partitionIndex + '}';
    }
}
