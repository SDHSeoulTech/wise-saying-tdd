package app.domain.wiseSaying.repository;

import app.domain.wiseSaying.Page;
import app.domain.wiseSaying.WiseSaying;

import java.util.Optional;

public interface WiseSayingRepository {
     WiseSaying save(WiseSaying wiseSaying);

     Page findAll(int itemsPerPage, int page);

     boolean deleteById(int id);

     Optional<WiseSaying> findById(int id);

     void build();

     void makeSampleData(int cnt);

     int count();

     Page findByKeword(String ktype, String kw, int itemsPerPage, int page);
}
