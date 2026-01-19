package utils;
 
import org.json.JSONObject;
 
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
 
public class JsonDataManager {
 
    private static JSONObject testData;
 
    static {
        try {
            InputStream is = JsonDataManager.class
                    .getClassLoader()
                    .getResourceAsStream("testdata.json");
            if (is == null) {
                throw new RuntimeException("testdata.json not found in resources");
            }
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            testData = new JSONObject(json);
        } catch (Exception e) {
            throw new RuntimeException("Unable to load testdata.json", e);
        }
    }
 
    public static JSONObject getData(String testId) {
        if (!testData.has(testId)) {
            throw new RuntimeException("No data found for testId: " + testId);
        }
        return testData.getJSONObject(testId);
    }
}