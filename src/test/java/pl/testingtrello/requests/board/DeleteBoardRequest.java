package pl.testingtrello.requests.board;

import io.restassured.response.Response;
import pl.testingtrello.secrets.Authentication;
import pl.testingtrello.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {
    public static Response deleteBoardResponse(String boardId) {

        return given()
                .when()
                .queryParam("key", Authentication.getApiKey())
                .queryParam("token", Authentication.getApiToken())
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
