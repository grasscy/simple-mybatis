package cn.grassc;

import cn.grassc.simple.mybais.Select;

public interface UserMapper {
    @Select("select count(*) from user")
    int count();

    @Select("select * from user where id=?")
    User find(int id);
}
