#!/bin/bash

HOME_PATH=$(pwd)

source set_kube_context.sh

echo "PROVISION SERVICES"
kubectl create namespace shipping
kubectl label namespace shipping istio-injection=enabled

cd customer-service
mvn clean install
docker build -t customer-service .
helm upgrade -i customer-service ./customer-service -f ./customer-service/values.yaml -n shipping
cd $HOME_PATH
cd order-service
mvn clean install
docker build -t order-service .
helm upgrade -i order-service ./order-service -f ./order-service/values.yaml -n shipping
cd $HOME_PATH
echo "SERVICES PROVISIONED"

