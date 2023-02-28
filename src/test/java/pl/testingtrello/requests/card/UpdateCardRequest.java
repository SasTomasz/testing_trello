package pl.testingtrello.requests.card;

import io.restassured.response.Response;
import pl.testingtrello.requests.base.BaseRequest;
import pl.testingtrello.url.TrelloUrl;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UpdateCardRequest {
    public static Response updatePutCardResponse(String cardId, Map<String, String> queryParams) {

        return given()
                .queryParams(queryParams)
                .spec(BaseRequest.requestSetup())
                .when()
                .put(TrelloUrl.getCardUrl(cardId))
                .then()
                .extract()
                .response();

    }
}
