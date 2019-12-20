package com.example.tobyExample;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DaoFactoryTest {

    private DaoFactory daoFactory;

    @BeforeEach
    public void setUp() {
        this.daoFactory = new DaoFactory();
    }

    @Test
    public void userDao_notNull() {
        assertThat(daoFactory.userDao())
                .isNotNull();
        
    }

}