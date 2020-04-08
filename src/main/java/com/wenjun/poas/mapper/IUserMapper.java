package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xuwenjun
 * @date 2020/3/26
 */
@Mapper
public interface IUserMapper {
    /**
     * add a user
     *
     * @param user
     */
    void addUser(User user);

    /**
     * delete a user
     *
     * @param username
     */
    void deleteUser(String username);

    /**
     * find user by username
     *
     * @param username
     * @return
     */
    User findUser(String username);

    /**
     * update password
     *
     * @param user
     * @return
     */
    boolean changePassword(User user);

    /**
     * change user role
     * @param user
     * @return
     */
    boolean changeRole(User user);

}
