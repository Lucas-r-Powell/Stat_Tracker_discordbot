package com.example;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.out.print(dotenv.get("ToddToken"));
    }
}