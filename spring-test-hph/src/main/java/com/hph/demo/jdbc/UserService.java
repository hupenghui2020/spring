package com.hph.demo.jdbc;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hph
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface UserService {

	void save(User user);

	User getUser();
}
