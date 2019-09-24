package org.huboy1.huboy1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.huboy1.huboy1.model.User;

@Mapper
public interface UserMapper {
    //@Select("select * from huboy.h_user")
    @Insert("insert into huboy.user1 (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    @Select("select * from huboy.user1 where token = #{token}")
    User findByToken(@Param("token") String token);
    //User select();
}
