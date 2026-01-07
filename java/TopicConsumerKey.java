package java;

import java.util.Objects;

public final class TopicConsumerKey {
    private final Integer topicId;
    private final Integer consumerId;

    public TopicConsumerKey(Integer topicId, Integer consumerId) {
        this.topicId = topicId;
        this.consumerId = consumerId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public Integer getConsumerId() {
        return consumerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TopicConsumerKey that = (TopicConsumerKey) o;
        return Objects.equals(topicId, that.topicId) && Objects.equals(consumerId, that.consumerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, consumerId);
    }

    @Override
    public String toString() {
        return "TopicConsumerKey{" + "topicId=" + topicId + ", consumerId=" + consumerId + '}';
    }
}
