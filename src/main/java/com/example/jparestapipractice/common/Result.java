package com.example.jparestapipractice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor
public class Result<T> {
    private T result;
}
