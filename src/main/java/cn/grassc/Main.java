package cn.grassc;

import cn.grassc.simple.mybatis.Configuration;
import cn.grassc.simple.mybatis.SqlSession;
import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2b8";
        String user = "root";
        String pwd = "root";

        // 配置config
        DataSource dataSource = new PooledDataSource(driver, url, user, pwd);
        Configuration configuration = new Configuration(dataSource);
        configuration.addMapper(UserMapper.class);
        configuration.addInterceptor(new UselessPlugin());

        // 获取session
        SqlSession session = new SqlSession(configuration);
        UserMapper mapper = session.getMapper(UserMapper.class);

        int i = mapper.count();
        System.out.println(i);

        User u = mapper.find(2);
        System.out.println(u);
    }
}
