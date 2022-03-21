# Kubernetes Helm Tutorial

This repo shows a local installation of Kubernetes (minikube) and some example services. Service configurations are
provisioned with Helm.

## Kubernetes with minikube

### Install minikube

Install minikube from <https://kubernetes.io/de/docs/setup/minikube/>

### Install kubectl

Install kubectl from <https://kubernetes.io/de/docs/tasks/tools/install-kubectl/>

### Start minikube

Make sure that previous minikube stuff is deleted with

```cmd
minikube delete
```

Create a new minikube instance with

```cmd
minikube start --cpus=2 --memory=5000
```

### Deploy service to Kubernetes

Build the sample _springboot-jpa-service_ with

```cmd
mvn clean install 
```

Start a MariaDB Docker container

```cmd
docker run --name springboot-jpa-service-database -e MYSQL_ROOT_PASSWORD=jenskohler -p3306:3306 -d mariadb:latest
```

Populate some sample test data

```sql
USE demo;
INSERT INTO customer (id, date_of_birth, first_name, last_name) VALUES ('1', '2022-01-01', 'homer', 'simpson');
INSERT INTO customer (id, date_of_birth, first_name, last_name) VALUES ('2', '2022-02-02', 'marge', 'simpson');
INSERT INTO customer (id, date_of_birth, first_name, last_name) VALUES ('3', '2022-03-03', 'lisa', 'simpson');

```

Test your local setup with accessing <http://localhost:8080/customers> (GET request). The previously inserted test data
should appear.

## Helm