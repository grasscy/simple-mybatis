package cn.grassc;

import cn.grassc.simple.mybatis.Select;

public interface UserMapper {
    @Select("select count(*) from user")
    int count();

    @Select("select * from user where id=?")
    User find(int id);
}
