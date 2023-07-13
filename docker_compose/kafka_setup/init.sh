#!/bin/bash

kafka-topics.sh --create --zookeeper "zookeeper:2181" --replication-factor 1 --partitions 1 --topic channel

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic channel --from-beginning