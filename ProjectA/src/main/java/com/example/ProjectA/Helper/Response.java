package com.example.ProjectA.Helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.swing.text.html.parser.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    private  boolean status;
    private String message;
    private  T entity;
}
