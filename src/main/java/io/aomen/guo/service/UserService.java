/**

* Title: UserServiceImpl.java

* Description: 

* Copyright: Copyright (c) 2017

* @author AMGuo

* @date 2017��8��7��

* @version 1.0

*/
package io.aomen.guo.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.aomen.guo.dao.UserDao;
import io.aomen.guo.entity.User;
import io.aomen.guo.web.formbean.UserFB;

/**
 * 
 * Title: UserServiceImpl
 * 
 * Description:
 * 
 * @author AMGuo
 * 
 * @date 2017��8��7��
 * 
 */
@Service("userService")
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 
     * @param id
     * @return
     */
    public User findOne(Long id) {
        return userDao.findOne(id);
    }

    /**
     * ���� Javadoc��
     * 
     * Title: findAll
     * 
     * Description:
     * 
     * @return
     * 
     * @see io.aomen.guo.service.UserService#findAll()
     * 
     */
    public List<User> findAll() {
        return userDao.findAll();
    }

    public Page<User> findPage(Pageable pageable) {
        return userDao.findAll(pageable);
    }

    /**
     * ���� Javadoc��
     * 
     * Title: save
     * 
     * Description:
     * 
     * @param entity
     * @return
     * 
     * @see io.aomen.guo.service.UserService#save(io.aomen.guo.entity.User)
     * 
     */
    /*
     * @Transactional(readOnly = false,isolation =
     * Isolation.SERIALIZABLE,propagation = Propagation.REQUIRED)
     */
    @Transactional(readOnly = false)
    public User save(User entity) {
        return userDao.save(entity);
    }

    /**
     * ���� Javadoc��
     * 
     * Title: delete
     * 
     * Description:
     * 
     * @param id
     * 
     * @see io.aomen.guo.service.UserService#delete(java.lang.String)
     * 
     */
    @Transactional(readOnly = false)
    public void delete(Long id) {
        userDao.delete(id);
    }

    /**
     * toEntity
     * 
     * @param userFB
     * @return
     */
    public User toEntity(UserFB userFB) {
        User user = new User();
        BeanUtils.copyProperties(userFB, user);
        return user;
    }

    public Page<UserFB> toPageFb(Page<User> users) {
        Page<UserFB> userFBs = users.map(new Converter<User, UserFB>() {

            @Override
            public UserFB convert(User source) {
                UserFB userFB = new UserFB();
                BeanUtils.copyProperties(source, userFB);
                return userFB;
            }
        });
        return userFBs;
    }

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
