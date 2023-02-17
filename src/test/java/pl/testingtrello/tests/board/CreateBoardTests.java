package pl.testingtrello.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.testingtrello.requests.board.CreateBoardRequest;
import pl.testingtrello.requests.board.DeleteBoardRequest;

class CreateBoardTests {

    @Test
    void CreateBoardTest() {
        String boardName = "NEW BOARD FROM JAVA";
        String boardId;
        final Response createBoardResponse = CreateBoardRequest.createBoardResponse(boardName);

        Assertions.assertEquals(HttpStatus.SC_OK, createBoardResponse.getStatusCode());

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));

        // DELETE BOARD
        boardId = json.getString("id");

        final Response deleteBoardResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertEquals(HttpStatus.SC_OK, deleteBoardResponse.getStatusCode());
    }
}
