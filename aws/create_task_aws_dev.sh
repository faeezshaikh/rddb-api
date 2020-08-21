#!/bin/bash

sed -e "s;%SPRING_PROFILES_ACTIVE%;dev;g" aws/ecs-task-template.json | \
sed -e "s;%ECS_ROLE%;arn:aws:iam::452309530346:role/ussd-dev-ecs-role;g" | \
sed -e "s;%AWS_ACCOUNT_ID%;452309530346;g"



