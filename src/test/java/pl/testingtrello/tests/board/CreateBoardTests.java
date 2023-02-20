package pl.testingtrello.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.testingtrello.requests.board.CreateBoardRequest;
import pl.testingtrello.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;

class CreateBoardTests {

    @Test
    void CreateBoardTest() {
        String boardName = "NEW BOARD FROM JAVA";
        String boardId;
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        // Create a new board
        final Response createBoardResponse = CreateBoardRequest.createBoardResponse(queryParams);

        Assertions.assertEquals(HttpStatus.SC_OK, createBoardResponse.getStatusCode());

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertEquals(boardName, json.getString("name"));

        // Delete the board
        boardId = json.getString("id");

        final Response deleteBoardResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertEquals(HttpStatus.SC_OK, deleteBoardResponse.getStatusCode());
    }
}
