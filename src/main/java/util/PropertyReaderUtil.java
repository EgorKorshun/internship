package util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Properties properties = new Properties();

    public static Properties getProperties() {
        return properties;
    }

    private PropertyReaderUtil() {
    }

    public static void loadProperties() {
        final String propertiesFileName = "src/main/resources/db.properties";
        try(InputStream inputStream = new FileInputStream(propertiesFileName)){
            properties.load(inputStream);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}