package com.example.tobyExample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTests {

    private User user;

    @BeforeEach
    public void setUp() {

        user = User.builder()
                .id("wlsdl8012")
                .name("Jin")
                .password("123123")
                .build();

    }

    @Test
    public void generate() {

        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo("wlsdl8012");
        assertThat(user.getName()).isEqualTo("Jin");
        assertThat(user.getPassword()).isEqualTo("123123");

    }

}