package pers.os467.management.service;

import pers.os467.management.base.response.ResponseEntity;
import pers.os467.management.entity.User;
import pers.os467.management.form.ChangePasswordForm;
import pers.os467.management.form.UpdateUserForm;
import pers.os467.management.utils.JwtUser;
import pers.os467.management.vo.GetUserByUidVo;
import pers.os467.management.vo.UserVo;

import java.util.List;
import java.util.Map;

public interface UserService{

    /**
     * 添加用户
     * @param user 用户信息
     * @return 影响记录条数
     */
    int addUser(User user);

    /**
     * 通过用户号获取到用户信息
     * @param uid 用户号
     * @return 用户信息
     */
    GetUserByUidVo getUserByUid(Integer uid);

    /**
     * 模糊查询获取用户列表条目数
     *
     * @param likeName 查询关键字
     * @param flag
     * @param precise
     * @return 条目数
     */
    int getUserListByLikeNameTotal( String likeName,String flag, Boolean precise);

    /**
     * 模糊查询获取用户列表
     * @param startIndex 起始索引
     * @param pageSize 分页大小
     * @param likeName 查询关键字
     * @param flag 查询字段标识
     * @param precise 是否精确查询
     * @return 查询结果
     */
    List<UserVo> getUserListByLikeName(int startIndex, int pageSize, String likeName, String flag, Boolean precise);

    /**
     * 更新用户
     * 根据携带的resetPassword选项选择是否重置密码
     * @param user 用户信息
     * @return 影响记录条数
     */
    int updateUser(UpdateUserForm user);

    /**
     * 根据用户号删除用户信息
     * @param uid 用户号
     * @return 影响记录条数
     */
    int deleteUserByUid(Integer uid);

    /**
     * 登录用户
     * @param username 登录名
     * @param password 密码(md5加密过的)
     * @return 查询到的用户信息
     */
    User loginUser(String username, String password);

    /**
     * 通过用户名查询到用户信息
     * @param username 用户名
     * @return 用户信息
     */
    User getUserByUserName(String username);

    /**
     * 通过登录用户和用户提供的密码更新用户密码
     * @param changePasswordForm
     * @param jwtUser
     * @return 返回实体信息
     */
    ResponseEntity changePassword(ChangePasswordForm changePasswordForm, JwtUser jwtUser);

    /**
     * 更新用户头像地址
     * @param avatarUrl 头像地址
     * @param jwtUser 登录用户
     * @return 返回影响记录条目数
     */
    int updateUserAvatar(String avatarUrl, JwtUser jwtUser);

    /**
     * 获取用户头像地址
     * @param jwtUser 登录用户
     * @return
     */
    ResponseEntity getUserAvatarUrl(JwtUser jwtUser);

    /**
     * 通过用户名获取用户头像
     * @param username
     * @return
     */
    ResponseEntity searchAvatarUrl(String username);

    /**
     * 通过用户名Id列表查询符合条件的用户，以用户id为组件封装返回结果
     * @param ids Id列表
     * @return 返回的结果集合
     */
    Map<Integer,User> getUsernameMapByIds(List<Integer> ids);

    /**
     * 根据角色id去筛选用户并获取到复合条件的用户id列表
     * @param receivedRids 符合条件的用户rid
     * @return 用户id列表
     */
    List<User> getUserIdListByRidList(List<Integer> receivedRids);
}

