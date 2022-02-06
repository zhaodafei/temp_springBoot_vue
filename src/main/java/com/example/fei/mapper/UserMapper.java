package com.example.fei.mapper;

import com.example.fei.domain.User;
import com.example.fei.domain.User2;
import org.apache.ibatis.annotations.Select;

// 这一层常用名字 dao mapper
// 在入口文件中使用 @MapperScan("com.example.*.mapper") 扫描
// @Mapper
public interface UserMapper {
    // 查询一条数据
    User selectUser(long id);

    User selectUser2(long id);

    // @Select("SELECT * FROM user WHERE id=#{id}")
    // User selectUser3(long id);

    User selectUser4(User user);

    User selectUser5(User user);

    User2 selectUser6(long id);

    User selectUser7(long id);
}
