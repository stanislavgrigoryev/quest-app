package com.example.questapp;

import lombok.Data;

@Data
public class Statistic {
    private String ipAddress;
    private String name;
    private int count= 0;
}
