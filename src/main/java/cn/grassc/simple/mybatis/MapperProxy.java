package cn.grassc.simple.mybatis;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Getter
@Setter
public class MapperProxy<T> implements InvocationHandler {
    private SqlSession sqlSession;
    private Class<T> mapper;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = new Object();
        Select annotation = method.getAnnotation(Select.class);
        if (annotation != null) {
            result = sqlSession.select(annotation.value(), method, args);
        }
        return result;
    }
}
