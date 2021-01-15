package com.hph.demo.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Types;
import java.util.List;

/**
 * @author hph
 */
@Component
public class UserServiceImpl implements UserService{

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource (DataSource dataSource) {

		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void save(User user) {

		jdbcTemplate.update("insert into user(name,age,sex) values (?,?,?)",
				new Object[]{user.getName(), user.getAge(), user.getSex()},
				new int[]{Types.VARCHAR, Types.INTEGER, Types.VARCHAR});
	}

	@Override
	public List<User> getUsers() {

		List<User> list = jdbcTemplate.query("select * from user", new Object[]{}, new UserRowMapper());
		return list;
	}
}
