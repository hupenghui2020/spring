package com.hph.demo.jdbc.mybatis;

import com.hph.demo.jdbc.User;

/**
 * @author hph
 */
public interface UserMapper {

	void insertUser(User user);

	User getUser(String name);
}
