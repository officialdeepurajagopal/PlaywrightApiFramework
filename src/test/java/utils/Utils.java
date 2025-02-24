package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.ReqPojo.CreateUser;

public class Utils {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String getBody(String title, String body, Integer userId)
    {
        return String.format("{\"title\": \"%s\", \"body\": \"%s\", \"userId\": %d}", title, body, userId);
    }

    public static String getCreateUserBody(String name, String job) throws JsonProcessingException {

        CreateUser createUser = new CreateUser();
        createUser.setName(name);
        createUser.setJob(job);

        return objectMapper.writeValueAsString(createUser);
    }
}
