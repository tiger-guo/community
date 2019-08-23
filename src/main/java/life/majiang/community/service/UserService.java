package life.majiang.community.service;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author:刘国虎
 * @ Company:XGLAB
 * @ Time: 2019/8/22 22:37
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);

        if(users.size()==0){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        }else {
            users.get(0).setGmtModified(System.currentTimeMillis());
            users.get(0).setAvatarUrl(user.getAvatarUrl());
            users.get(0).setName(user.getName());
            users.get(0).setToken(user.getToken());
            userMapper.updateByPrimaryKeySelective(users.get(0));
        }
    }
}
