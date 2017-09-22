package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    //更新经过非空判断的
    int updateByPrimaryKeySelective(User record);

    //直接全部更新
    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    //查询email是否存在
    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password, @Param("userId") Integer userId);

    //查询email是否被其他用户占用
    int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);
}