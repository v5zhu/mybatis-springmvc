package com.v5zhu.distribute.repository;


import com.v5zhu.distribute.entity.User;
import com.v5zhu.distribute.repository.MyBatisRepository;

/**
 * Created by zhuxl on 2015/5/20.
 */

@MyBatisRepository
public interface UserMybatisDao {

    int login(User user);

    User findByLoginName(String loginName);

    int createUser(User user);

    int modifyUser(User user);

    int deleteUser(Long userId);
}
