package app.domain.wisesaying.repository;

import app.domain.wisesaying.Page;
import app.domain.wisesaying.WiseSaying;

import java.util.List;
import java.util.Optional;

public interface WiseSayingRepository {
     WiseSaying save(WiseSaying wiseSaying);

     Page findAll();

     boolean deleteById(int id);

     Optional<WiseSaying> findById(int id);

     void build();

    void makeSampleData(int cnt);

    int count();
}
