#!/bin/bash

sed -e "s;%SPRING_PROFILES_ACTIVE%;sandbox;g" aws/ecs-task-template.json | \
sed -e "s;%ECS_ROLE%;arn:aws:iam::635283063535:role/ussd-sb-ecs-role;g" | \
sed -e "s;%AWS_ACCOUNT_ID%;635283063535;g"



