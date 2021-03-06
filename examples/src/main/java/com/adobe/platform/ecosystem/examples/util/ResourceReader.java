
/*
 *  Copyright 2017-2018 Adobe.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.adobe.platform.ecosystem.examples.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by vedhera on 8/25/2017.
 */

/**
 * Utility to read properties.
 */
public class ResourceReader {
    private final static String PROPERTY_FILE = "config.properties";

    public static final Logger logger = Logger.getLogger(ResourceReader.class.getName());

    public String getProperty(String key) {
        return readPropertiesFromResource().getProperty(key);
    }

     public Properties readPropertiesFromResource() {
        return readConfigPropertiesFromResource(null);
    }

     public Properties readConfigPropertiesFromResource(String env) {
        String propFileName = ResourceReader.PROPERTY_FILE;
        propFileName = (env == null ? "dev/" + propFileName : env + "/" + propFileName);
        propFileName = "/" + propFileName;
        return loadProperties(propFileName);
    }

    public Properties readDefaultConfigFromResource() {
        return loadProperties("/" + ResourceReader.PROPERTY_FILE);
    }

    private Properties loadProperties(String propertyFilePath) {
        Properties prop = new Properties();
        InputStream inputStream;
        inputStream = getClass().getResourceAsStream(propertyFilePath);
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.severe("Error while readPropertiesFromResource : " + e.getMessage());
        }
        return prop;
    }
}