package com.aatapi;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import com.aatapi.domainobject.AATDO;
import com.aatapi.service.aat.AATService;

@SpringBootApplication
public class ApplicantTestApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(ApplicantTestApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AATService aatService) {
        return args -> {
            // read json and write to db
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            URL jsonUrl = new URL("https://lod-cloud.net/json/getty-aat");


            TypeReference<List<AATDO>> typeReference = new TypeReference<List<AATDO>>(){};
            try {
                System.out.println(jsonUrl);
                System.out.println("==============");
                List<AATDO> aatList = mapper.readValue(jsonUrl,typeReference);
                aatService.save(aatList);
                System.out.println(aatService.list());
                System.out.println("AATs Saved!");
            } catch (IOException e){
                e.printStackTrace();
                System.out.println("Unable to save aatList: " + e.getMessage());
            }
        };
    }
}
