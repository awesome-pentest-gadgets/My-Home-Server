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

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * The server.
 * 
 * @author stefv
 */
public class Server {

    /**
     * The logger.
     */
    private static final Logger LOG = Logger.getLogger(Server.class.getName());

    /**
     * The main method of the server.
     * 
     * @param args Arguments.
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        final Server server = new Server();
        server.start();
    }

    /**
     * Start the servers.
     */
    private void start() {

        LOG.info("Servers starting...");

        // Prepare the logs
        try {
            if (Settings.getServerLog() != null) {
                final Logger logger = Logger.getLogger("");
                final Handler fileHandler = new FileHandler(Settings.getServerLog().getAbsolutePath(), true);
                fileHandler.setFormatter(new SimpleFormatter());
                fileHandler.setLevel(Level.INFO);
                fileHandler.setEncoding("UTF-8");
                logger.addHandler(fileHandler);
            }
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }

        final WebServer webServer = new WebServer();
        webServer.start();

        final DBServer dbServer = new DBServer();
        dbServer.start();

        LOG.info("Servers started.");
    }
}
