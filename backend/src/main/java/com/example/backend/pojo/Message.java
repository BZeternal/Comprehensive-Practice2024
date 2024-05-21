package com.example.backend.pojo;

import lombok.Data;

@Data
public class Message {
    private Integer toName;
    private String message;
    private Long time;
}
