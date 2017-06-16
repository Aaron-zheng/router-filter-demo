# router-filter-demo

## 双向前置机

1. 设置服务端为 SSL，并且确认为客户端验证（双向验证）

2. 请求会先进入全局过滤器，判断该请求的客户端证书是否合法，是否在已经被吊销列表中

3. 如果客户端证书合法，则会进入之后的自定义过滤器

4. 最后请求会进入到 Zuul 的过滤器，并且根据 CustomLocator 的预配置文件进行请求转发

5. 当需要在线更新路由配置的时候，需要主动发出路由更新事件