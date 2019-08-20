package com.lonely.zo.learnxd.mapper;

import com.lonely.zo.learnxd.dto.QuestionDTO;
import com.lonely.zo.learnxd.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert({"insert into t_question(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})"})
    void create(Question question);

    @Select({"select * from t_question limit #{offset},#{size}"})
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select({"select count(1) from t_question"})
    Integer count();

    @Select({"select * from t_question where creator=#{userId} limit #{offset},#{size}"})
    List<Question> listByuserId(@Param("userId") Integer userId,
                        @Param(value = "offset") Integer offset,
                        @Param(value = "size") Integer size);

    @Select({"select count(1) from t_question where creator=#{userId}"})
    Integer countByuserid(@Param("userId") Integer userId);

    @Select({"select * from t_question where id=#{id}"})
    Question getById(@Param("id") Integer id);
}
