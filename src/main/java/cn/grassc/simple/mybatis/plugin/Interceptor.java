package cn.grassc.simple.mybatis.plugin;

public interface Interceptor {
    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);
}
