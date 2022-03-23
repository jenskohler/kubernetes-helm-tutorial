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

### (optional) Test your application on your local machine

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


Start the Springboot application locally. 

Test your local setup with accessing <http://localhost:8080/customers> (GET request). The previously inserted test data
should appear.

End your local application.

### Deploy service to Kubernetes

Build the sample _springboot-jpa-service_ with
```cmd
mvn clean install 
```

Deploy your application in a Docker container and provide it to minikube.

Access your minikube environment and let your Docker point to minikube:

```cmd
minikube docker-env

eval $(minikube -p minikube docker-env)
```

List your minikube Docker images:

```cmd
docker images
```

Build a Docker image from your application. Change in your project folder (springboot-jpa-service) and use:

```cmd
docker build -t customer-service .
```

Verify that your image is present in the list of images

```cmd
docker images
```

## Helm

### Provision Mariadb as backend

The following steps are only required, if a new version of MariaDB should be 
integrated. As there is already a chart in the _customer-service/charts_ folder, 
these steps can be omitted. 

Add the Bitnami Helm repository to your list of Helm repositories: 
```cmd 
helm repo add bitnami https://charts.bitnami.com/bitnami
```

Pull the Mariadb image, which you would like to use, 
note that the latest release is pulled if nothing else is provided: 

```cmd 
helm pull bitnami/mariadb
```

Extract the downloaded tar.gz file:
```cmd 
tar -xvfz mariadb-10.4.2.tgz
```

Examine your downloaded and unzipped folders and adapt the values according to your needs.

### Install the Mariadb Helm chart:

Install the preconfigured MariaDB Helm chart:
```cmd
cd customer-service/charts/

helm install mariadb ./mariadb
```

Upgrade the Maridb Helm chart:
```cmd
helm upgrade -i mariadb ./mariadb
```

### Provision the customer service

Provision the service configuration with Helm:
```cmd
cd <PROJECT_ROOT>/

helm install customer-service ./customer-service
```

Start the minikube dashboard with:
```cmd
minikube dashboard
```

It takes a while unitl all pods are green and running. Be patient!


Remove the service:
```cmd
helm uninstall customer-service ./customer-service-chart
```


