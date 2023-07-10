package com.example.demo.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthorityEnum {

    READ("READ"),

    WRITE("WRITE"),

    DELETE("DELETE"),

    UPDATE("UPDATE");

    private final String code;

}
