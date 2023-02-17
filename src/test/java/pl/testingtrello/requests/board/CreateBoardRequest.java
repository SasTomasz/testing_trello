package pl.testingtrello.requests.board;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pl.testingtrello.secrets.Authentication;
import pl.testingtrello.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class CreateBoardRequest {
    public static Response createBoardResponse(String boardName) {

        return given()
                .contentType(ContentType.JSON)
                .queryParam("name", boardName)
                .queryParam("key", Authentication.getApiKey())
                .queryParam("token", Authentication.getApiToken())
                .when()
                .post(TrelloUrl.getBoardsUrl())
                .then()
                .extract()
                .response();
    }
}