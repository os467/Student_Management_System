package pers.os467.management.mapper;


import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.os467.management.entity.User;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper{
    int addUser(@Param("user") User user);

    User getUserByUid(@Param("uid")Integer uid);

    int updateUser(@Param("user")User user);

    int getUserListByLikeNameTotal(@Param("likeName") String likeName,
                                   @Param("col") String col,
                                   @Param("precise") Boolean precise);

    List<User> getUserListByLikeName(@Param("startIndex") int startIndex,
                                     @Param("pageSize") int pageSize,
                                     @Param("likeName") String likeName,
                                     @Param("col") String col,
                                     @Param("precise")  Boolean precise);

    int deleteUserByUid(@Param("uid")Integer uid);

    User loginUser(@Param("username") String username,
                   @Param("password") String password);

    User getUserByUserName(@Param("username") String username);

    int changePassword(@Param("uid") Integer uid,@Param("password") String password);

    int updateUserAvatar(@Param("uid")Integer uid,@Param("avatarUrl") String avatarUrl);

    String searchAvatarUrl(@Param("username") String username);

    List<User> getUserByIds(@Param("ids") List<Integer> ids);

    @MapKey("uid")
    Map<Integer, User> getUsernameMapByIds(@Param("ids") List<Integer> ids);

    List<User> getUserIdListByRidList(@Param("rids") List<Integer> receivedRids);
}

