package pl.testingtrello.requests.board;

import io.restassured.response.Response;
import pl.testingtrello.requests.base.BaseRequest;
import pl.testingtrello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardResponse(Map<String, String> queryParams) {

        return given()
                .queryParams(queryParams)
                .spec(BaseRequest.requestSetup())
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}