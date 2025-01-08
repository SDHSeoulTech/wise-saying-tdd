package app.domain.wisesaying;

import java.util.List;

public class Page {
    public List<WiseSaying> wiseSayings;
    public int totalPages;
    public int totalItems;

    public List<WiseSaying> getWiseSayings() {
        return wiseSayings;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
