package pl.testingtrello.requests.list;

import io.restassured.response.Response;
import pl.testingtrello.requests.base.BaseRequest;
import pl.testingtrello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateListRequest {
    public static Response createListResponse(Map<String, String> queryParams) {

        return given()
                .queryParams(queryParams)
                .spec(BaseRequest.requestSetup())
                .when()
                .post(TrelloUrl.getListsUrl())
                .then()
                .extract()
                .response();
    }

}
