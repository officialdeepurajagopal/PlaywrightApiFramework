package utils;

public class Utils {

    public static String getBody(String title, String body, Integer userId)
    {
        return String.format("{\"title\": \"%s\", \"body\": \"%s\", \"userId\": %d}", title, body, userId);
    }
}
