package com.hph.demo.jdbc;

import com.hph.demo.jdbc.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * @author hph
 */
@Component
public class UserServiceImpl implements UserService{

	@Resource
	private UserMapper userMapper;

	@Override
	public void save(User user) {

		userMapper.insertUser(user);
		throw new RuntimeException("测试事务");
		/*jdbcTemplate.update("insert into user(name,age,sex) values (?,?,?)",
				new Object[]{user.getName(), user.getAge(), user.getSex()},
				new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});*/
	}

	@Override
	public User getUser() {

		//List<User> list = jdbcTemplate.query("select * from user", new Object[]{}, new UserRowMapper());
		return userMapper.getUser("sdawda");
	}
}
