package com.v5zhu.distribute.api.impl;


import com.v5zhu.distribute.api.UserService;
import com.v5zhu.distribute.dto.UserDto;
import com.v5zhu.distribute.entity.User;
import com.v5zhu.distribute.repository.UserMybatisDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springside.modules.mapper.BeanMapper;

/**
 * Created by zhuxl@paxsz.com on 2016/7/25.
 */
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMybatisDao userMybatisDao;

    @Override
    public UserDto findByLoginName(String loginName) {
        User user=userMybatisDao.findByLoginName(loginName);
        if (user!=null)
            return BeanMapper.map(user,UserDto.class);
        return null;
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user=userMybatisDao.findByLoginName(userDto.getLoginName());
        if(user!=null){
            throw new Exception("该用户名已被占用");
        }
        user=BeanMapper.map(userDto,User.class);
        user.setDeleted(0);
        int result=userMybatisDao.createUser(user);
        if(result!=1){
            throw new Exception("添加用户失败");
        }
    }

    @Override
    public void modifyUser(Long userId, UserDto userDto) throws Exception {
        User user=BeanMapper.map(userDto,User.class);
        user.setId(userId);
        int result=userMybatisDao.modifyUser(user);
        if(result!=1){
            throw new Exception("修改用户信息失败");
        }
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        int result=userMybatisDao.deleteUser(userId);
        if(result!=1){
            throw new Exception("删除用户失败");
        }
    }
}
