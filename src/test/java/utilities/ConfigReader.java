package utilities;
// 2. Setting up ConfigReader Class

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public static Properties properties;

    // Approach 1 - using abstract FileReader Class:
    public static void initializeProperties() {
        try {
            FileReader fileReader = new FileReader("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(fileReader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Approach 2 - using abstract FileInputStream Class:
    public static void initProperties() {
        try {
            String configPropertiesPath = "src/test/resources/config.properties";
            FileInputStream fileInputStream = new FileInputStream(configPropertiesPath);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // .getConfigProperty() method returns the value, stored inside the "key" String variable, passed as an argument to this method
    public static String getConfigProperty(String key){
        return properties.getProperty(key).trim();
    }
}
