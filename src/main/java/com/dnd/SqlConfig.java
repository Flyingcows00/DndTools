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
    public String insertSkills(@Value("classpath:sql/insertSkills.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String insertSavingThrows(@Value("classpath:sql/insertSavingThrows.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String insertAbilities(@Value("classpath:sql/insertAbilities.sql") Resource resource) throws IOException {
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
    public String getAllMonsters(@Value("classpath:sql/getAllMonsters.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    @Bean
    public String getMonstersByName(@Value("classpath:sql/getMonstersByName.sql") Resource resource) throws IOException {
        return resourceToString(resource);
    }

    private String resourceToString(Resource resource) throws IOException {
        File file = resource.getFile();
        String str = new String(Files.readAllBytes(file.toPath()));
        return str;
    }

}
