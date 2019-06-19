package cn.grassc;

import cn.grassc.simple.mybatis.plugin.Interceptor;
import cn.grassc.simple.mybatis.plugin.Invocation;
import cn.grassc.simple.mybatis.plugin.Plugin;

public class UselessPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("进入插件……");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
