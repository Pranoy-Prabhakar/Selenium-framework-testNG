package nl.postnl.pom.utils;

import nl.postnl.pom.constants.Env;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(Env.TST));
        switch (Env.valueOf(env)){
            case TST:
                properties = PropertyUtils.propertyLoader("src/test/resources/tst_config.properties");
                break;
            case PROD:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default:
                throw new IllegalStateException("Invalid env type : "+ env);
        }
    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop!=null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the tst_config.properties file");
    }

    public String getUserName(){
        String prop = properties.getProperty("userName");
        if(prop!=null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the tst_config.properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop!=null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the tst_config.properties file");
    }
}
