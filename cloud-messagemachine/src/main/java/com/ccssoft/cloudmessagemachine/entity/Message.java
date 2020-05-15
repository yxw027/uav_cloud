package com.ccssoft.cloudmessagemachine.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author moriarty
 * @date 2020/5/15 13:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String id;
    private String coordinate;
    private String statuss;
    private String speed;
    private String direction;
    private String altitude;
    private String battery;
    private Timestamp time;
    private String message;

    public Message(String str) {
        this.message = str;
    }
}
