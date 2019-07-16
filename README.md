# spring-integration-gateway-kafka-sample

Request–response, or request–reply, is one of the basic methods computers use to communicate with each other, in which the first computer sends a request for some data and the 
second responds to the request. Usually, there is a series of such interchanges until the complete message is sent; browsing a web page is an example of request–response 
communication. Request–response can be seen as a telephone call, in which someone is called and they answer the call.

Request–response is a message exchange pattern in which a requestor sends a request message to a replier system which receives and processes the request, ultimately returning a 
message in response. This is a simple, but powerful messaging pattern which allows two applications to have a two-way conversation with one another over a channel. This pattern 
is especially common in client–server architectures.

For simplicity, this pattern is typically implemented in a purely synchronous fashion, as in web service calls over HTTP, which holds a connection open and waits until the response
is delivered or the timeout period expires. However, request–response may also be implemented asynchronously, with a response being returned at some unknown later time. When 
a synchronous system communicates with an asynchronous system, it is referred to as "sync over async" or "sync/async". This is common in enterprise application integration (EAI) 
implementations where slow aggregations, time-intensive functions, or human workflow must be performed before a response can be constructed and delivered.

In contrast, one-way computer communication, which is like the push-to-talk or "barge in" feature found on some phones and two-way radios, sends a message without waiting for 
a response. Sending an email is an example of one-way communication, and another example are fieldbus sensors, such as most CAN bus sensors, which periodically and autonomously 
send out their data, whether or not any other devices on the bus are listening for it. (Most of these systems use a "listen before talk" or other contention-based protocol so 
multiple sensors can transmit periodic updates without any pre-coordination.) 

[WIKIPEDIA](https://en.wikipedia.org/wiki/Request%E2%80%93response)

This a sample project that shows how to implement "sync over async" or "sync/async" solution using spring integration and spring cloud stream through apache kafka.

Apache Kafka is by design inherently asynchronous. Hence Request-Reply semantics is not natural in Apache Kafka. This challenge is however not new. The Request Reply Enterprise 
Integration Pattern provides a proven mechanism for synchronous message exchange over asynchronous channels:

![Alt text](gateway_diagram.png?raw=true "Diagram")

## References
- [Request–response](https://en.wikipedia.org/wiki/Request%E2%80%93response)
- [Request-Reply in Spring Cloud Stream] (https://github.com/spring-cloud/spring-cloud-stream/issues/815)
- [Synchronous Request-Reply using Apache Kafka] (https://callistaenterprise.se/blogg/teknik/2018/10/26/synchronous-request-reply-over-kafka/)
- [Synchronous Kafka: Using Spring Request-Reply ](https://dzone.com/articles/synchronous-kafka-using-spring-request-reply-1)
