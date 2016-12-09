package com.hbl.utils.properties;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
* @className:Config.java
* @classDescription:读取配置文件类
* @author:hbl
* @createTime:2016年8月15日
*/
public class ConfigUtils{  
	
	private static Logger log = Logger.getLogger(ConfigUtils.class);
    
    /** 国际化资源 */
	public static ResourceBundle resourceBundle;
	//private static BufferedInputStream inputStream;

	static {
		  resourceBundle = ResourceBundle.getBundle("init");
		  /*String proFilePath = System.getProperty("user.dir") + "/config/init.properties";  
		  try {  
			  inputStream = new BufferedInputStream(new FileInputStream(proFilePath));  
			  resourceBundle = new PropertyResourceBundle(inputStream);  
		  } catch (FileNotFoundException e) {  
			  e.printStackTrace();  
		  } catch (IOException e) {  
			  e.printStackTrace();  
		  }*/
	}
	  
    public static String getString(String key) {
        try {
            if(!resourceBundle.containsKey(key)) {
                return null;
            }
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
        	log.error(e);
            return null;
        }
    }
    
    public static String getString(String key, String defaultValue) {
        try {
            if(!resourceBundle.containsKey(key)) {
                return defaultValue;
            }
            return resourceBundle.getString(key);
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return defaultValue;
        } catch (NullPointerException e) {
        	log.error(e);
            return defaultValue;
        }
    }
    
    public static int getInt(String key) {
        try {
            if(!resourceBundle.containsKey(key)) {
                return 0;
            }
            return Integer.parseInt(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return 0;
        } catch (NullPointerException e) {
        	log.error(e);
            return 0;
        }
    }
    
    public static int getInt(String key, int defaultValue) {
        try {
            if(!resourceBundle.containsKey(key)) {
                return defaultValue;
            }
            return Integer.parseInt(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return defaultValue;
        } catch (NullPointerException e) {
        	log.error(e);
            return defaultValue;
        }
    }
    
    public static boolean getBoolean(String key) {
    	try {
            if(!resourceBundle.containsKey(key)) {
                return false;
            }
            return Boolean.parseBoolean(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
        	log.error(e);
            return false;
        }
    }
    
    public static boolean getBoolean(String key, boolean defaultValue) {
    	try {
            if(!resourceBundle.containsKey(key)) {
                return defaultValue;
            }
            return Boolean.parseBoolean(resourceBundle.getString(key));
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return defaultValue;
        } catch (NullPointerException e) {
        	log.error(e);
            return defaultValue;
        }
    }
    
    public static String getString(String key, Object[] args) {
        try {
            return MessageFormat.format(getString(key), args);
        } catch (MissingResourceException e) {
        	log.error(e);
        	e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
        	log.error(e);
            return null;
        }
    }
   
}  