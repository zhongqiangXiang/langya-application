# 简介
  注册中心：langya-eureka-center
  配置中心：langya-conf
  服务提供：langya-services
  服务消费：langya-consumer
  服务网关：langya-zuul
  zipkin服务收集中心：langya-zipkin-server-es/langya-zipkin-server-rabbitmq

## 启动顺序：
  注册中心-> 配置中心 (-> zipkin服务收集中心) -> 服务提供 -> 服务消费 -> 服务网关

## 访问入口：
  服务网关

## 调用链：
  服务网关 -> 服务消费 -> 服务提供 






# 参考

<https://github.com/cralor7/springcloud>  
<https://github.com/shunyang/spring-cloud-microservice-study>

<https://github.com/whyalwaysmea/Spring-Security>

<https://github.com/dyc87112/SpringCloudBook>
