package pers.os467.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import pers.os467.management.base.ResponseUtils;
import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.common.Constant;
import pers.os467.management.entity.Role;
import pers.os467.management.entity.User;
import pers.os467.management.mapper.UserMapper;
import pers.os467.management.form.ChangePasswordForm;
import pers.os467.management.form.UpdateUserForm;
import pers.os467.management.service.RoleService;
import pers.os467.management.service.UserService;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.vo.GetUserByUidVo;
import pers.os467.management.vo.UserVo;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Override
    public User loginUser(String username, String password) {
        User user = null;
        if (username != null && password != null){
            user = userMapper.loginUser(username, password);
        }
        return user;
    }

    @Override
    public int deleteUserByUid(Integer uid) {
        int i = 0;
        if (uid != null){
            i = userMapper.deleteUserByUid(uid);
        }
        return i;
    }

    @Override
    public User getUserByUserName(String username){
        return userMapper.getUserByUserName(username);
    }

    @Override
    public ResponseEntity changePassword(ChangePasswordForm changePasswordForm, JwtUser jwtUser) {
        String password = changePasswordForm.getPassword();
        Integer uid = jwtUser.getUid();
        if (uid != null && password != null){
            int i = userMapper.changePassword(uid, password);
            if (i > 0){
                return ResponseUtils.getSuccessResult();
            }
            return ResponseUtils.getFailResult("修改失败");
        }

        return ResponseUtils.getFailResult("请检查token是否可用");
    }

    @Override
    public int updateUserAvatar(String avatarUrl, JwtUser jwtUser) {
        Integer uid = jwtUser.getUid();
        if (uid != null && avatarUrl != null){
            return userMapper.updateUserAvatar(uid, avatarUrl);
        }
        return 0;
    }

    @Override
    public ResponseEntity getUserAvatarUrl(JwtUser jwtUser) {
        Integer uid = jwtUser.getUid();
        if (uid != null){
            User user = userMapper.getUserByUid(uid);
            if (user == null){
                return ResponseUtils.getFailResult("获取头像数据失败");
            }
            //用户头像地址
            String avatarUrl = user.getAvatarUrl();

            return ResponseUtils.getSuccessResult(avatarUrl);
        }

        return ResponseUtils.getFailResult("请检查token是否可用");
    }

    @Override
    public ResponseEntity searchAvatarUrl(String username) {
        String avatarUrl = userMapper.searchAvatarUrl(username);
        if(avatarUrl == null || avatarUrl.length() == 0){
            avatarUrl = Constant.DEFAULT_IMG_URL;
        }
        return ResponseUtils.getSuccessResult(avatarUrl);
    }

    @Override
    public Map<Integer,User> getUsernameMapByIds(List<Integer> ids) {
        return userMapper.getUsernameMapByIds(ids);
    }

    @Override
    public List<User> getUserIdListByRidList(List<Integer> receivedRids) {
        return userMapper.getUserIdListByRidList(receivedRids);
    }

    @Override
    public int addUser(User user) {
        User userByUserName = getUserByUserName(user.getUsername());
        if (userByUserName != null){
            //说明存在同名用户
            return -1;
        }
        //默认头像地址
        user.setAvatarUrl(Constant.DEFAULT_IMG_URL);
        return userMapper.addUser(user);
    }

    @Override
    public GetUserByUidVo getUserByUid(Integer uid) {
        User user = userMapper.getUserByUid(uid);

        GetUserByUidVo getUserByUidVo = new GetUserByUidVo(user.getUid(),
                user.getUsername(),
                user.getRid()
        );
        return getUserByUidVo;
    }

    @Override
    public int updateUser(UpdateUserForm userPo) {
        int i = 0;
        User user;
        if (userPo.getUid() != null){
            //如果需要重置密码则重置密码
            if (userPo.getResetPassword()){
                String password = DigestUtils.md5DigestAsHex(Constant.REST_PASSWORD.getBytes(StandardCharsets.UTF_8));
                user = new User(userPo.getUid(),userPo.getUsername(),password,userPo.getRid(),null);
            }else {
                //否则依旧使用旧密码
                User userByUid = userMapper.getUserByUid(userPo.getUid());
                user = new User(userPo.getUid(),userPo.getUsername(),userByUid.getPassword(),userPo.getRid(),null);
            }
            i = userMapper.updateUser(user);
        }
        return i;
    }


    @Override
    public int getUserListByLikeNameTotal(String likeName, String flag, Boolean precise) {
        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return 0;
        }else {
            return userMapper.getUserListByLikeNameTotal(likeName,col,precise);
        }
    }

    @Override
    public List<UserVo> getUserListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise) {

        String col = Constant.FLAG_MAP.get(flag);
        if (col == null){
            return null;
        }else {
            List<User> userListByLikeName = userMapper.getUserListByLikeName(startIndex, pageSize, likeName,col,precise);
            List<UserVo> userVoList = toUserVoList(userListByLikeName);
            return userVoList;
        }
    }


    private List<UserVo> toUserVoList(List<User> userList) {

        List<UserVo> userVoList = userList.stream().map((user) -> {
            Role roleByRid = roleService.getRoleByRid(user.getRid());

            UserVo userVo = new UserVo(user.getUid(),
                    user.getUsername(),
                    roleByRid.getRname()
            );
            return userVo;
        }).collect(Collectors.toList());
        return userVoList;
    }
}
