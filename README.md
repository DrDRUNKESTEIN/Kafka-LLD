# Kafka-LLD
Problem statement:
Design an In-Memory Distributed Queue like Kafka.

Requirements Gathering:
The queue should be in-memory and does not require access to the file system.
There can be multiple topics in the queue.
A (string) message can be published on a topic by a producer/publisher and consumers/subscribers topic to receive the messages.
There can be multiple producers and consumers.
A producer can publish to multiple topics.
A consumer can listen from multiple topics.
The consumer should print "<consumer_id> received " on receiving the message.
The queue system should be multithreaded, i.e., messages can be produced or consumed in parallel producers/consumers.
Possible Use cases
User should be able to add topic
User should be able to add producers 3. User should be able to add consumers
User should be able to publish message
System should be able to consume message
MVP
Create Producer
Create Consumer
Create Topic
Publish Message
Consume Message
Not in scope
Security