package io.github.util;

public class ServiceUtils {

    public static String getSystemEnvOrProperty(String key) {
        String value = System.getenv(key);

        if (value == null) {
            value = System.getProperty(key);
        }

        return value;
    }

}
