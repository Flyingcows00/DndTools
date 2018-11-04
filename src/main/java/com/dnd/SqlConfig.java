package com.dnd;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class SqlConfig {

    @Bean
    public String insertMonster(@Value("classpath:sql/insertMonster.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String insertAction(@Value("classpath:sql/insertAction.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String getAllMonsterNames(@Value("classpath:sql/getAllMonsterNames.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String getMonsterByName(@Value("classpath:sql/getMonsterByName.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String getActionsByMonsterName(@Value("classpath:sql/getActionsByMonsterName.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String addUser(@Value("classpath:sql/addUser.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String removeUser(@Value("classpath:sql/removeUser.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String getUsers(@Value("classpath:sql/getUsers.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String insertSpell(@Value("classpath:sql/insertSpell.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    public String getUser(@Value("classpath:sql/getUser.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    private String resourceToString(Resource resource) throws IOException {
        File file = resource.getFile();
        String str = new String(Files.readAllBytes(file.toPath()));
        return str;
    }

}
