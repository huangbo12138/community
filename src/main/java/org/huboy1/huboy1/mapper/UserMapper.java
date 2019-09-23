package org.huboy1.huboy1.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.huboy1.huboy1.model.User;

@Mapper
public interface UserMapper {
    //@Select("select * from huboy.h_user")
    @Insert("insert into huboy.h_user (account_id,name,token,gmt_create,gmt_modified) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    //User select();
}
