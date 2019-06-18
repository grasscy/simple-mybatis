package cn.grassc.simple.mybais;

import lombok.Getter;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Configuration {
    private DataSource dataSource;
    private Map<Class<?>, MapperProxy<?>> mappers = new HashMap<>();

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
}
