package pl.testingtrello.tests.e2e;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import pl.testingtrello.requests.board.CreateBoardRequest;
import pl.testingtrello.requests.board.DeleteBoardRequest;
import pl.testingtrello.requests.board.ReadBoardRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateAndDeleteBoardTest {
    private static String boardId;
    private static final String boardName = "Board For Test";
    private static final String boardDescription = "Test if there is possible get board";

    @Test
    @Order(1)
    void createBoardTest() {
        Map<String, String> boardParams = new HashMap<>();
        boardParams.put("name", boardName);
        boardParams.put("desc", boardDescription);
        final Response response = CreateBoardRequest.createBoardResponse(boardParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        boardId = json.getString("id");
    }

    @Test
    @Order(2)
    void readBoardTest() {
        Response boardResponse = ReadBoardRequest.readBoardResponse(boardId);

        Assertions.assertThat(boardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonBoard = boardResponse.jsonPath();

        Assertions.assertThat(jsonBoard.getString("name")).isEqualTo(boardName);
        Assertions.assertThat(jsonBoard.getString("desc")).isEqualTo(boardDescription);
    }

    @Test
    @Order(3)
    void deleteBoardTest() {
        Response response = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    @Test
    @Order(4)
    void getNonexistentBoardTest() {
        Response response = ReadBoardRequest.readBoardResponse(boardId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(5)
    void deleteNonexistentBoardTest() {
        Response response = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

}
