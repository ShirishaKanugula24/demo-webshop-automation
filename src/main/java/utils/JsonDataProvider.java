package utils;
 
import org.json.JSONObject;

import org.testng.annotations.DataProvider;
 
import java.lang.reflect.Method;

import java.util.HashMap;

import java.util.Iterator;

import java.util.Map;
 
public class JsonDataProvider {
 
    @DataProvider(name = "jsonData", parallel = true)

    public static Object[][] jsonData(Method method) {

        String testId = method.getName();   // e.g. "TC05_validLogin"

        JSONObject data = JsonDataManager.getData(testId);
 
        Map<String, String> map = new HashMap<>();

        Iterator<String> keys = data.keys();

        while (keys.hasNext()) {

            String key = keys.next();

            map.put(key, data.get(key).toString());

        }
 
        return new Object[][]{

                { map }

        };

    }

}
 