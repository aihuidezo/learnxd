package com.lonely.zo.learnxd.mapper;

import com.lonely.zo.learnxd.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert({"insert into t_user (name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})"})
    void insert(User user);
}
