loadbalancer服务器负载均衡 
    在调用微服务接口时，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程服务调用技术
nginx是服务器负载均衡
    客户端所以请求都会交给Nginx，然后nginx实现转发请求，即负载均衡是由服务端实现的