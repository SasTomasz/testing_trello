package pl.testingtrello.url;

public class TrelloUrl {
    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARD_URL = "/boards";
    private static final String LIST_URL = "/lists";
    private static final String CARDS_URL = "/cards";

    private TrelloUrl() {

    }

    public static String getBoardsUrl() {
        return BASE_URL + BOARD_URL;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }

    public static String getListsUrl() {
        return BASE_URL + LIST_URL;
    }

    public static String getCardsUrl() {
        return BASE_URL + CARDS_URL;
    }

    public static String getCardUrl(String cardId) {
        return getCardsUrl() + "/" + cardId;
    }
}
