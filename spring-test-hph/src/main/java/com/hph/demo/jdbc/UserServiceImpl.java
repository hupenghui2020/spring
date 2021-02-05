package com.hph.demo.jdbc;

import com.hph.demo.jdbc.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author hph
 */
@Component
public class UserServiceImpl implements UserService{

	private JdbcTemplate jdbcTemplate;

	@Resource
	private UserMapper userMapper;

	@Autowired
	public void setDataSource (DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(User user) {

		userMapper.insertUser(user);

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
