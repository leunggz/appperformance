package common;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private static String appName;
    private static String activityName;

    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/config.properties"));
            appName = properties.getProperty("appName");
            activityName = properties.getProperty("activityName");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("can't find config document");
            appName = null;
        }
    }

    public static String getAppName(){
        return appName;
    }

    public static String getActivityName(){
        return activityName;
    }

    public static void main(String[] args){
        System.out.println(Config.getAppName());
        System.out.println(Config.getActivityName());

    }
}
