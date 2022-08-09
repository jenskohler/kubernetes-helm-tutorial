#!/bin/bash

HOME_PATH=$(pwd)

echo "INSTALL HELM CHARTS"
kubectl create namespace istio-ingress
kubectl label namespace istio-ingress istio-injection=enabled
kubectl create namespace istio-system
helm upgrade -i istio-base istio/base -n istio-system
helm upgrade -i istiod istio/istiod --namespace istio-system
helm upgrade -i istio-ingress istio/gateway -n istio-ingress
echo "FINISHED HELM CHARTS"

