package cn.grassc.simple.mybatis;

import cn.grassc.simple.mybatis.plugin.Interceptor;
import lombok.Getter;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Configuration {
    private final DataSource dataSource;
    private Map<Class<?>, MapperProxy<?>> mappers = new HashMap<>();
    private List<Interceptor> interceptors = new ArrayList<>();

    public Configuration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> void addMapper(Class<T> mapper) {
        mappers.put(mapper, new MapperProxy<>());
    }

    public <T> T getMapper(Class<T> mapper, SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = (MapperProxy<T>) mappers.get(mapper);
        mapperProxy.setMapper(mapper);
        mapperProxy.setSqlSession(sqlSession);
        return (T) Proxy.newProxyInstance(mapper.getClassLoader(), new Class[]{mapper}, mapperProxy);
    }

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public Object newExecutor(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }
}
