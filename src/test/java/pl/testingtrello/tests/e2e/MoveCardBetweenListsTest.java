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
import pl.testingtrello.requests.card.CreateCardRequest;
import pl.testingtrello.requests.card.UpdateCardRequest;
import pl.testingtrello.requests.list.CreateListRequest;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MoveCardBetweenListsTest {
    private static String boardId;
    private static String firstListId;
    private static String secondListId;
    private static String cardId;

    @Test
    @Order(1)
    void createBoardTest() {
        String boardName = "MoveCardBetweenListsTest";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", boardName);
        final Response response = CreateBoardRequest.createBoardResponse(queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(boardName);

        boardId = json.getString("id");
    }

    @Test
    @Order(2)
    void createFirstListTest() {
        String listName = "FirstList";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", listName);
        queryParams.put("idBoard", boardId);
        final Response response = CreateListRequest.createListResponse(queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(listName);

        firstListId = json.getString("id");
    }

    @Test
    @Order(3)
    void createSecondListTest() {
        String listName = "SecondList";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", listName);
        queryParams.put("idBoard", boardId);
        final Response response = CreateListRequest.createListResponse(queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(listName);

        secondListId = json.getString("id");

    }

    @Test
    @Order(4)
    void addCardToFirstListTest() {
        String cardName = "FirstCard";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", cardName);
        queryParams.put("idList", firstListId);
        final Response response = CreateCardRequest.createCardResponse(queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(cardName);

        cardId = json.getString("id");
    }

    @Test
    @Order(5)
    void moveCardBetweenListsTest() {
        String cardName = "Edited First Card Name";
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("name", cardName);
        queryParams.put("idList", secondListId);
        final Response response = UpdateCardRequest.updatePutCardResponse(cardId, queryParams);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath json = response.jsonPath();

        Assertions.assertThat(json.getString("name")).isEqualTo(cardName);
        Assertions.assertThat(json.getString("idList")).isEqualTo(secondListId);
    }

    @Test
    @Order(6)
    void deleteBoardTest() {
        final Response response = DeleteBoardRequest.deleteBoardResponse(boardId);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
