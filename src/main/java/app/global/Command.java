package app.global;

import java.util.HashMap;
import java.util.Map;

public class Command {

    String actionName;
    Map<String, String> paramMap;
    // 쪼개기 작업

    public Command(String cmd) {

        paramMap = new HashMap<>();
        // 삭제?id=1
        // 이름=차태진 (key-value)
        String[] cmdBits = cmd.split("\\?"); //[삭제, id=1]
        actionName = cmdBits[0];

        if(cmdBits.length < 2) {
            return;
        }

        String queryString = cmdBits[1];

        String[] params = queryString.split("&"); // ["key1=val1", "key2=val2"]
        for(String param : params) {
            String[] paramBits = param.split("=", 2);

            if(paramBits.length < 2) {
                continue;
            }

            paramMap.put(paramBits[0], paramBits[1]);
        }

    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key){
        return paramMap.get(key);
    }

    public int getParamAsInt(String key, int defaultValue) {
        try {
            String param = paramMap.get(key);
            return Integer.parseInt(param);
        } catch (NumberFormatException e) {
            return defaultValue;
        }

    }

    public boolean isSearchCommand() {
        return (getParam("keywordType") != null || getParam("keyword") != null);

    }
}
