/**
 * Copyright 2008-2016 VANPOPERYNGHE Stéphane
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
 * @author svanp
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
     * Param for the port.
     */
    private static final String PORT_PARAM = "port";

    /**
     * Default port.
     */
    private static final String DEFAULT_PORT = "8080";

    /**
     * Constructs the settings class.
     */
    public Settings() {
    }

    /**
     * Returns the port.
     * 
     * @return the port.
     */
    public static int getPort() {
        return Integer.parseInt(System.getProperty(PORT_PARAM, DEFAULT_PORT).trim());
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
