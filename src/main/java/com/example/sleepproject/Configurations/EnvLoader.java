package com.example.sleepproject.Configurations;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
}