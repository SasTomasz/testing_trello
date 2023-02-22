package pl.testingtrello.secrets;

public class Authentication {
    private static final String API_KEY = "Your API Key";
    private static final String API_TOKEN = "Your API Token";

    private Authentication() {

    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getApiToken() {
        return API_TOKEN;
    }
}