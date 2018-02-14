package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigContainer {
    // Статический экземпляр этого класса
    private static ConfigContainer instance;
    // Настройки тестовой среды
    private Properties properties = new Properties();
    // Относительный путь к файлу с настройками тестовой среды
    private static final String propertiesFileName = "src/test/resources/config/config.properties";

    /**
     * Возвращает статический экземпляр класса
     */
    public static synchronized ConfigContainer getInstance() {
        if (instance == null) instance = new ConfigContainer();
        return instance;
    }

    /**
     * Возвращает значение параметра среды по его ключу
     */
    public String getConfigParameter(String key) {
        return properties.getProperty(key);
    }

    /**
     * Загружает настройки тестовой среды из файла
     */
    public void loadConfig() {
        InputStream input = null;
        try {
            input = new FileInputStream(propertiesFileName);
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
