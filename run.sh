#!/bin/bash
env $(cat .env | xargs) ./mvnw spring-boot:run
