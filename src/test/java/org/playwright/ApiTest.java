package org.playwright;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.RequestOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pojo.DataPojo;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class ApiTest extends BaseTest{

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);
    private final String testEnvironment = System.getProperty("env", "dev");
    private final String filePath = String.format("src/test/resources/testdata/%s.json", testEnvironment);


    // Read JSON file and convert to Java object




    @Test
    public void testGetUsers() {
        logger.info("Initiating get request to /users");
        APIResponse response = request.get("/api/users?page=2");
        Assert.assertEquals(response.status(), 200, "Status code mismatch");
        logger.info("List of users {}", response.text());
    }

    @Test
    public void testCreateUser() throws IOException {
        logger.info("Initiating post request to /createUser");
        ObjectMapper objectMapper = new ObjectMapper();

        DataPojo dataPojo = objectMapper.readValue(new File(filePath), DataPojo.class);

        APIResponse response = request.post("/api/users",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(Utils.getCreateUserBody(dataPojo.getName(), dataPojo.getJob()))
        );

        Assert.assertEquals(response.status(), 201, "Status code mismatch");
        logger.info("Post Passed with response {}", response.text());
    }


}
