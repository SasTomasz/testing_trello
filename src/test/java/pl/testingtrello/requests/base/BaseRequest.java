package pl.testingtrello.requests.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pl.testingtrello.secrets.Authentication;

public class BaseRequest {

    public static RequestSpecification requestSetup() {
        RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
        requestBuilder.setContentType(ContentType.JSON);
        requestBuilder.addQueryParam("key", Authentication.getApiKey());
        requestBuilder.addQueryParam("token", Authentication.getApiToken());

        return requestBuilder.build();
    }
}
