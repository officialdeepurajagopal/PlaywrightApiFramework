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

public class ApiTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);
    private final String testEnvironment = System.getProperty("env", "dev");
    private final String filePath = String.format("src/test/resources/testdata/%s.json", testEnvironment);
    Playwright playwright;
    APIRequestContext request;

    // Read JSON file and convert to Java object


    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://jsonplaceholder.typicode.com"));
    }

    @Test
    public void testGetUsers() {
        logger.info("Initiating get request to /users");
        APIResponse response = request.get("/users");
        Assert.assertEquals(response.status(), 200, "Status code mismatch");
        logger.info("Get Passed with response {}", response.text());
    }

    @Test
    public void testCreatePost() throws IOException {
        logger.info("Initiating post request to /posts");
        ObjectMapper objectMapper = new ObjectMapper();

        DataPojo dataPojo = objectMapper.readValue(new File(filePath), DataPojo.class);

        APIResponse response = request.post("/posts",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(Utils.getBody(dataPojo.getTitle(), dataPojo.getBody(), dataPojo.getUserId()))
        );

        Assert.assertEquals(response.status(), 201, "Status code mismatch");
        logger.info("Post Passed with response {}", response.text());
    }

    @AfterClass
    public void teardown() {
        logger.info("Closing Playwright and disposing request context...");
        request.dispose();
        playwright.close();
    }
}
