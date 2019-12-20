package com.example.tobyExample;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

    private String id;
    private String name;
    private String password;

}
