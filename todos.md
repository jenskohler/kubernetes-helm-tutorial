# ToDos

## Ensure referential integrity between customer and order

- actual behavior: in an order it is possible to add arbitraty customer ids 
- expected behavior: only customer ids that are in the customer service should be allowed to be added to orders

## Integrate Istio as a service mesh

- actual behavior: as minikube has no load balancer integrated, exposing services to the host has to be done manually
- expected behavior: create a service mesh that exposes minikube services automatically

## Create some more services

- actual behavior: there are only 2 services 
- expected behavior: create some more services, such that more advanced implementations become possible

## Ensure transaction safety over microservices

- actual behavior: inconsistencies between the customer and order service are possible when the communication between the services fail
- expected behavior: implement a Saga pattern, that ensures transaction safety over microservices

## Create a nice web frontend for all the services

- actual behavior: there is no web frontend at all
- expected behavior: create a nice web frontend with e.g. bootstrap