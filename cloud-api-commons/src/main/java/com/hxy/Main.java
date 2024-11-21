package com.hxy;

import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();  // 默认时区
        System.out.println(now);
    }
}