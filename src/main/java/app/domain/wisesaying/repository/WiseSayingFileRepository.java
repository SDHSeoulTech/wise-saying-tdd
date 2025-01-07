package app.domain.wisesaying.repository;

import app.domain.wisesaying.WiseSaying;
import app.standard.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository{

    public final List<WiseSaying> wiseSayingList;
    private static final String DB_PATH = "db/test/wiseSaying/";
    private int lastId;

    public WiseSayingFileRepository() {

        this.wiseSayingList = new ArrayList<>();
        System.out.println("파일 DB 사용");
    }

    public WiseSaying save(WiseSaying wiseSaying) {

        // 파일 저장

        Util.Json.writeAsMap(getFilePath(id).formatted(wiseSaying.getId()), wiseSaying.toMap());

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingList;
    }

    public boolean deleteById(int id) {
        return Util.File.delete("db/wiseSaying/%d.json".formatted(id));
    }

    public Optional<WiseSaying> findById(int id) {
        return  wiseSayingList.stream()
                .filter(w -> w.getId() == id)
                .findFirst();
    }
    private String getFilePath(int id) {
        
    }

}
