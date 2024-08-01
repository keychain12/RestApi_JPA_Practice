package com.example.jparestapipractice.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
@AllArgsConstructor
public class ListResult <T>{

    private List<T> result;
}
