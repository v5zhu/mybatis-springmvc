package mybatis;


import com.v5zhu.dubbo.commons.repository.MyBatisRepository;
import com.v5zhu.dubbo.po.entity.User;

/**
 * Created by zhuxl on 2015/5/20.
 */

@MyBatisRepository
public interface UserMybatisDao {
    User findByLoginName(String loginName);

    int createUser(User user);

    int modifyUser(User user);

    int deleteUser(Long userId);
}
