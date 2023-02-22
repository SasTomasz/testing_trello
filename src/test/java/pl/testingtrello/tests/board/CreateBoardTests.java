package pl.testingtrello.tests.board;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.testingtrello.requests.board.CreateBoardRequest;
import pl.testingtrello.requests.board.DeleteBoardRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

class CreateBoardTests {

    @DisplayName("Create board with valid data")
    @ParameterizedTest(name = "Board name: {0}")
    @MethodSource("sampleBoardNameData")
    void CreateBoardTest(String boardName) {
        String boardId;
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);

        // Create a new board
        final Response createBoardResponse = CreateBoardRequest.createBoardResponse(queryParams);

        Assertions.assertThat(createBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = createBoardResponse.jsonPath();
        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        // Delete the board
        boardId = json.getString("id");

        final Response deleteBoardResponse = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(deleteBoardResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    private static Stream<Arguments> sampleBoardNameData() {
        return Stream.of(
                Arguments.of("NEW BOARD FROM JAVA"),
                Arguments.of("NEW_BOARD_FROM_JAVA"),
                Arguments.of("newBoardFromJava"),
                Arguments.of("!"),
                Arguments.of("@"),
                Arguments.of("#"),
                Arguments.of("$"),
                Arguments.of("%"),
                Arguments.of("^"),
                Arguments.of("&"),
                Arguments.of("*"),
                Arguments.of("("),
                Arguments.of(")")
        );
    }
}
