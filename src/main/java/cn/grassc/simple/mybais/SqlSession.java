package cn.grassc.simple.mybais;

import lombok.Getter;

import java.lang.reflect.Method;


@Getter
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = new Executor();
    }

    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    public Object select(String sql, Method method, Object[] args) throws Exception {
        return executor.query(configuration, sql, args, method.getReturnType());
    }
}
