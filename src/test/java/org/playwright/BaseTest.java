package org.playwright;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

@Slf4j
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    Playwright playwright;
    APIRequestContext request;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        request = playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("https://reqres.in/"));
    }

    @AfterClass
    public void teardown() {
        logger.info("Closing Playwright and disposing request context...");
        request.dispose();
        playwright.close();
    }
}
