 Kaiburr Task 2 – Kubernetes Deployment of Java + MongoDB App

This project containerizes a Java-based Task Manager API (from Task 1) and deploys it on a local Kubernetes cluster using **kubectl** and **YAML manifests**. It also sets up a MongoDB container with persistent storage and service exposure.

---

##  Tech Stack

- Java 17
- MongoDB
- Docker
- Kubernetes (kubectl, Minikube or Docker Desktop)
- YAML for deployments
- MongoDB Java Driver

---

##  Folder Structure

kaiburr-task2/
├── backend/ # Java source code and pom.xml
│ └── src/main/java/...
│ └── pom.xml
├── k8s/ # Kubernetes manifests
│ ├── mongo-deployment.yaml
│ ├── mongo-service.yaml
│ ├── mongo-pv.yaml
│ ├── deployment.yaml # Java app deployment
│ └── service.yaml
├── Dockerfile # Dockerfile for Java app
├── README.md

yaml
Copy
Edit

---

## Prerequisites

- ✅ Java 17+ installed
- ✅ Docker installed and running
- ✅ Kubernetes CLI (`kubectl`) installed
- ✅ Minikube or Docker Desktop (with Kubernetes enabled)
- ✅ MongoDB JAR dependencies (already included or pulled by Maven)

---
