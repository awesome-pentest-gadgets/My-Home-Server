/**
 * Copyright 2008-2017 stefv
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mhs.server;

import java.io.File;

/**
 * Settings class.
 * 
 * @author stefv
 */
public class Settings {

    /**
     * Param for the access log path.
     */
    private static final String ACCESS_LOG_PARAM = "accessLog";

    /**
     * Param for the server log path.
     */
    private static final String SERVER_LOG_PARAM = "serverLog";

    /**
     * Param for the directory of the database.
     */
    private static final String DB_DIRECTORY_PARAM = "dbDirectory";

    /**
     * Param for the port for the HTTP server.
     */
    private static final String HTTP_PORT_PARAM = "httpPort";

    /**
     * Param for the port for the DB server.
     */
    private static final String DB_PORT_PARAM = "dbPort";

    /**
     * Default HTTP port.
     */
    private static final String DEFAULT_HTTP_PORT = "5000";

    /**
     * Default DB port.
     */
    private static final String DEFAULT_DB_PORT = "9001";

    /**
     * Default DB directory.
     */
    private static final String DEFAULT_DB_DIRECTORY = "./db";

    /**
     * Constructs the settings class.
     */
    private Settings() {
    }

    /**
     * Returns the HTTP port.
     * 
     * @return the HTTP port.
     */
    public static int getHTTPPort() {
        return Integer.parseInt(System.getProperty(HTTP_PORT_PARAM, DEFAULT_HTTP_PORT).trim());
    }

    /**
     * Returns the DB port.
     * 
     * @return the DB port.
     */
    public static int getDBPort() {
        return Integer.parseInt(System.getProperty(DB_PORT_PARAM, DEFAULT_DB_PORT).trim());
    }

    /**
     * Returns the DB directory.
     * 
     * @return the DB directory.
     */
    public static String getDBDirectory() {
        return System.getProperty(DB_DIRECTORY_PARAM, DEFAULT_DB_DIRECTORY).trim();
    }

    /**
     * Returns the server log file.
     * 
     * @return the server log file.
     */
    public static File getServerLog() {
        if (System.getProperty(SERVER_LOG_PARAM) != null) {
            return new File(System.getProperty(SERVER_LOG_PARAM).trim());
        }
        return null;
    }

    /**
     * Returns the access log file.
     * 
     * @return the access log file.
     */
    public static File getAccessLog() {
        if (System.getProperty(ACCESS_LOG_PARAM) != null) {
            return new File(System.getProperty(ACCESS_LOG_PARAM).trim());
        }
        return null;
    }
}
