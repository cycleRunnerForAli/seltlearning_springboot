package com.cyclerunner.nevermore.mapper;

import com.cyclerunner.nevermore.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert("insert into question " +
            "(title,desc,gmt_create,gmt_modify,creator,tag) " +
            "values (#{title},#{desc},#{gmt_create},#{gmt_modify},#{creator},#{tag})")
    void addQuestion(Question question);

}
