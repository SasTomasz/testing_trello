package pl.testingtrello.secrets;

public class Authentication {
    private static final String API_KEY = "42d92c85930b214a66ad9f8dcc1612a0";
    private static final String API_TOKEN = "ATTA4c398e1fe50d0a88c66b0fb21d57fff205f70c03023dc1b81a993cb7c7c83d391E7B2AC1";

    private Authentication() {

    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getApiToken() {
        return API_TOKEN;
    }
}
