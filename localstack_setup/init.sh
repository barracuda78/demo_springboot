#!/bin/bash

set -euo pipefail

AWS="awslocal --endpoint-url=http://localhost:4566"
TOPIC="arn:aws:sns:us-east-1:000000000000:productmanagement-shared-dde-poms-core-all-entities-topic.fifo"
AEQ="http://localhost:4566/000000000000/productmanagement-shared-dde-poms-core-all-entities-queue.fifo"
ASQ="http://localhost:4566/000000000000/productmanagement-shared-dde-poms-sync-admin-entities-queue.fifo"
OUT="/var/lib/localstack/answer.json"

aws configure set aws_access_key_id foo
aws configure set aws_secret_access_key bar
aws configure set default.region us-east-1
aws configure set output json

$AWS cloudformation create-stack --stack-name queues_stack --template-body file:///var/lib/localstack/template.yaml

$AWS sqs list-queues

$AWS sns list-topics

# check subscriptions
$AWS sns list-subscriptions-by-topic --topic-arn $TOPIC >> $OUT;

# publish test message to the topic
$AWS sns publish --topic-arn $TOPIC --message-group-id uniqueGroupId --message test-message-1 >> $OUT;

#receive message from subscriber and print to file
$AWS sqs receive-message --queue-url $AEQ >> $OUT;
$AWS sqs receive-message --queue-url $ASQ >> $OUT;
