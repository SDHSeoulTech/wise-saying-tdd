package app.domain.wisesaying.repository;

import app.domain.wisesaying.WiseSaying;
import app.domain.wisesaying.WiseSayingService;
import app.global.AppConfig;
import app.standard.Util;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository{

    private static final String DB_PATH = AppConfig.getDbPath() + "/wiseSaying";
    private static final String ID_FILE_PATH = DB_PATH + "/lastId.txt";
    private static final String BUILD_PATH = DB_PATH + "/build/data.json";
    public WiseSayingFileRepository() {
        System.out.println("파일 DB 사용");
        Init();
    }

    public void Init() {
        if(!Util.File.exists(ID_FILE_PATH)) {
            Util.File.createFile(ID_FILE_PATH);
        }

        if(!Util.File.exists(DB_PATH)) {
            Util.File.createDir(DB_PATH);
        }

    }

    public WiseSaying save(WiseSaying wiseSaying) {

        boolean isNew = wiseSaying.isNew();

        if(isNew) {
            wiseSaying.setId(getLastId() + 1);
        }

        // 파일 저장
        Util.Json.writeAsMap(getFilePath(wiseSaying.getId()), wiseSaying.toMap());

        if(isNew) {
            setLastId(wiseSaying.getId());
        }

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return Util.File.getPaths(DB_PATH).stream()
                .map(Path::toString)
                .filter(path -> path.endsWith(".json"))
                .map(Util.Json::readAsMap)
                .map(WiseSaying::fromMap)
                .toList();
    }

    public boolean deleteById(int id) {
        return Util.File.delete(getFilePath(id));
    }

    public Optional<WiseSaying> findById(int id) {
        String path = getFilePath(id);
        Map<String, Object> map = Util.Json.readAsMap(path);

        if (map.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(WiseSaying.fromMap(map));
    }

    public static String getFilePath(int id) {
        return DB_PATH + "/" + id + ".json";
    }

    public int getLastId() {
        String idStr = Util.File.readAsString(ID_FILE_PATH);

        if(idStr.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setLastId(int id) {
        Util.File.write(ID_FILE_PATH, id);
    }

    public void build() {

        List<Map<String, Object>> mapList = findAll().stream()
                .map(WiseSaying::toMap)
                .toList();

        String jsonStr = Util.Json.listToJson(mapList);

        Util.File.write(BUILD_PATH, jsonStr);
    }

    public static String getBuildPath() {
        return BUILD_PATH;
    }
}
