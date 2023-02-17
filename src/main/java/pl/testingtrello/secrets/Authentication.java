package pl.testingtrello.secrets;

public class Authentication {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_TOKEN = "YOUR_API_TOKEN";

    private Authentication() {

    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getApiToken() {
        return API_TOKEN;
    }
}
