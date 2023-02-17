package pl.testingtrello.url;

public class TrelloUrl {
    private static final String BASE_URL = "https://api.trello.com/1";
    private static final String BOARD_URL = "/boards";

    private TrelloUrl() {

    }

    public static String getBoardsUrl() {
        return BASE_URL + BOARD_URL;
    }

    public static String getBoardUrl(String boardId) {
        return getBoardsUrl() + "/" + boardId;
    }
}
