package cn.grassc.simple.mybais;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Executor {
    public <T> T query(Configuration configuration, String sql, Object[] args, Class<T> resultType) throws Exception {
        T result = null;
        try (Connection connection = configuration.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            if (args != null) {
                for (int i = 1; i <= args.length; i++) {
                    statement.setObject(i, args[i - 1]);
                }
            }
            ResultSet resultSet = statement.executeQuery();
            if (resultType.isPrimitive()) {
                while (resultSet.next()) {
                    result = resultSet.getObject(1, resultType);
                }
            } else {
                result = resultType.getConstructor().newInstance();
                while (resultSet.next()) {
                    Field[] fields = resultType.getDeclaredFields();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        field.set(result,
                                resultSet.getObject(field.getName()));
                    }
                }
            }
        }
        return result;
    }
}
