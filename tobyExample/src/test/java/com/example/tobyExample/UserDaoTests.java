package com.example.tobyExample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTests {

    private DaoFactory daoFactory;
    private UserDao userDao;
    private User user;

    @BeforeEach
    public void setUp() {
        daoFactory = new DaoFactory();

        userDao = daoFactory.userDao();

        user = User.builder()
                .id("wlsdl8012")
                .name("Jin")
                .password("1234")
                .build();
    }

    @Test
    public void getCount_add_countIncrease() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();
        userDao.add(user);
        assertThat(userDao.getCount()).isEqualTo(1);

    }

    @Test
    public void add_countIncrease() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();

        Integer before = userDao.getCount();
        userDao.add(user);

        assertThat(userDao.getCount())
                .isEqualTo(before + 1);

    }

    @Test
    public void deleteAll_countIsZero() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();
        assertThat(userDao.getCount())
                .isEqualTo(0);

    }

    @Test
    public void get_add_sameName() throws SQLException, ClassNotFoundException {

        userDao.deleteAll();
        userDao.add(user);

        User got = userDao.get(user.getId());

        assertThat(got.getName())
                .isEqualTo(user.getName());

    }
}