# RabbitMQ-Implementation-Spring-Boot-Java

start rabbit MQ docker

```
docker run -d --hostname myrabbit -p 5000:15672 -p 5672:5672 --name rabitmq-springboot -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management

```

from project root directory

```
./gradlew clean build

```
after successful build run the project

```
java -jar <jarfile>

```

Test the APIs from postman

## Note
Default queue,exchange and routing key are bounded through beans.
To add a queue,exchange and routing key we have to do this manually by coding.In this example i also implemented that.I exposed one api for creating a complete dynamic queue with exchange and routing key bouned to that new queue.

One important thing is that without routing key bounded already we can publish message but it wont be published actually as there is no exchange and queue are bounded to that routing key.

queue,exchange must need to be created and routing key must need to bounded before use.If we call a non exists queue to broadcast a message then it would be failed.


