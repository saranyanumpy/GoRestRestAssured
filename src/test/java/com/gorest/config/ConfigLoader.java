package com.gorest.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigLoader {

    private static ConfigLoader configLoader;
    private final Config config;

    private ConfigLoader() {
        String env = System.getProperty("env", "qa");  // Default to "qa" if not specified
        Yaml yaml = new Yaml();

        InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream("config.yaml");
        if (inputStream == null) {
            throw new RuntimeException("❌ config.yaml not found in resource folder");
        }

        Map<String, Object> yamlMap = yaml.load(inputStream);
        Object envConfig = yamlMap.get(env);

        if (envConfig == null) {
            throw new RuntimeException("❌ Environment config not found for: " + env);
        }

        // ✅ Convert selected env block to Config object
        String dumpedYaml = yaml.dump(envConfig);
        this.config = yaml.loadAs(dumpedYaml, Config.class);
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null) {
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public Config getConfig() {
        return config;
    }
}
