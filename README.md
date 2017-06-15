# router-filter-demo

## 双向前置机

进入 InitialFilter 的 doFilter，判断证书是否合法，并且是否在已经被吊销

进入 CustomFilter 的 shouldFilter，判断是否进入 run

进入 CustomFilter 的 run，通过 RequestContext.getCurrentContext() 可以做业务逻辑

进入 CustomFilter 的 filterType，返回该过滤器属于 pre 阶段
