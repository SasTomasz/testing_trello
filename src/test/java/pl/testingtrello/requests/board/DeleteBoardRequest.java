package pl.testingtrello.requests.board;

import io.restassured.response.Response;
import pl.testingtrello.requests.base.BaseRequest;
import pl.testingtrello.url.TrelloUrl;

import static io.restassured.RestAssured.given;

public class DeleteBoardRequest {
    public static Response deleteBoardResponse(String boardId) {

        return given()
                .spec(BaseRequest.requestSetup())
                .when()
                .delete(TrelloUrl.getBoardUrl(boardId))
                .then()
                .extract()
                .response();
    }
}
