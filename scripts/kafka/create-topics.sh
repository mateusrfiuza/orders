#!/bin/bash

# Wait for Kafka to be ready
while ! kafka-topics --list --zookeeper kafka-zookeeper:2181; do
    sleep 1
done

# Create Kafka topics
kafka-topics --create --zookeeper kafka-zookeeper:2181 --replication-factor 1 --partitions 1 --topic order_created_event
