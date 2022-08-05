#!/bin/bash

echo "SET MINIKUBE CONTEXT"
minikube docker-env
eval $(minikube -p minikube docker-env)
echo "MINIKUBE CONTEXT SET"


