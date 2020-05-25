package com.wenjun.poas.mapper;

import com.wenjun.poas.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xuwenjun
 * @date 2020/3/26
 */
@Mapper
public interface IUserMapper {
    /**
     * add a user
     *
     * @param user user
     */
    void addUser(User user);

    List<User> findAll();

    /**
     * delete a user
     *
     * @param id id
     */
    void deleteUser(String id);

    /**
     * find user by username
     *
     * @param username username
     * @return user
     */
    User findUser(String username);

    /**
     * update password
     *
     * @param user user
     */
    void changePassword(User user);

    /**
     * change user role
     *
     * @param user user
     */
    void changeRole(User user);

    User findById(String id);

    void changeEmail(User user);

    void updateUser(User user);

}
