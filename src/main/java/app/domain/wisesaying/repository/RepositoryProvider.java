package app.domain.wisesaying.repository;

import app.global.AppConfig;

public class RepositoryProvider {

    public static WiseSayingRepository provide() {
//        if(AppConfig.isFileDb()) {
//            return new WiseSayingFileRepository();
//        } else {
//            return new WiseSayingMemRepository();
//        }
        return new WiseSayingFileRepository();
    }
}
