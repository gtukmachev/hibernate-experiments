package tga.hibernate_experiments.cache;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class GsonUtils {

    public static final Gson gson = new Gson();
    public static final Type empMapStringToIntType = new TypeToken<Map<String, Integer>>(){}.getType();

}
