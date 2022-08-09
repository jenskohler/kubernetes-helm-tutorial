#!/bin/bash

# Script starts minikube, sets env variables, creates service images, and deployes them to minikube.
HOME_PATH=$(pwd)

echo "STARTING DOCKER"
# Modify for Linux/Windows boxes accordingly
open -a Docker
read -p "Press enter to continue if Docker has already been started"
echo "DOCKER STARTED"

echo "STARTING MINIKUBE"
minikube delete
read -p "Press enter to continue if Minikube has already been deleted"
minikube start --cpus=8 --memory=13000 --driver=docker
read -p "Press enter to continue if Minikube has already been initialized"
echo "MINIKUBE STARTED"

echo "PROVISION ISTIO"
# source install_istio.sh
echo "ISTIO PROVISIONED"

echo "PROVISION SERVICES"
source provision_services.sh
echo "SERVICES PROVISIONED"

