#!/bin/bash

HOME_PATH=$(pwd)

source set_kube_context.sh

echo "INSTALL HELM CHARTS"
helm repo add istio https://istio-release.storage.googleapis.com/charts
helm repo update
kubectl create namespace istio-system
helm upgrade -i istio-base istio/base -n istio-system
helm upgrade -i istiod istio/istiod -n istio-system --wait
kubectl create namespace istio-ingress
kubectl label namespace istio-ingress istio-injection=enabled
helm upgrade -i istio-ingress istio/gateway -n istio-ingress
echo "FINISHED HELM CHARTS"

