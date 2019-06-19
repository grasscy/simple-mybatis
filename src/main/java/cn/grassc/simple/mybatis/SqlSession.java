package cn.grassc.simple.mybatis;

import lombok.Getter;

import java.lang.reflect.Method;


@Getter
public class SqlSession {
    private final Configuration configuration;
    private final Executor executor;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = (Executor) configuration.newExecutor(new Executor() {});
    }

    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public Object select(String sql, Method method, Object[] args) throws Exception {
        return executor.query(configuration, sql, args, method.getReturnType());
    }
}
