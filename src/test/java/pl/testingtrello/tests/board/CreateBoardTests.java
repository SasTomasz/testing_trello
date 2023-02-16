package pl.testingtrello.tests.board;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

class CreateBoardTests {
    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARD_URL = "/boards";
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_TOKEN = "YOUR_API_TOKEN";

    @Test
    void CreateBoardTest() {
        String boardName = "NEW BOARD FROM JAVA";
        String boardId;
        final Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("name", boardName)
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .when()
                .post(BASE_URL + BOARD_URL)
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.SC_OK, response.getStatusCode());

        JsonPath json = response.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));

        // DELETE BOARD
        boardId = json.getString("id");

        final Response response1 = given()
                .when()
                .queryParam("key", API_KEY)
                .queryParam("token", API_TOKEN)
                .pathParam("boardId", boardId)
                .delete(BASE_URL + BOARD_URL + "/{boardId}")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(HttpStatus.SC_OK, response1.getStatusCode());
    }
}
