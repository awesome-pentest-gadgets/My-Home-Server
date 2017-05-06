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

import java.util.logging.Logger;

import org.hsqldb.Server;
import org.hsqldb.persist.HsqlProperties;

/**
 * @author stefv
 */
public class DBServer extends AbstractServer {

    /**
     * The logger.
     */
    private static final Logger LOG = Logger.getLogger(DBServer.class.getName());

    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        startHSQLDB();
    }

    /**
     * Start the HSQLDB server.
     */
    private void startHSQLDB() {

        // Set the properties
        final HsqlProperties props = new HsqlProperties();
        props.setProperty("server.port", Settings.getDBPort());
        props.setProperty("server.database.0", "file:" + Settings.getDBDirectory() + "/mhs;");
        props.setProperty("server.dbname.0", "mhs");

        // Start the server
        try {
            final Server hsqldb = new Server();
            hsqldb.setProperties(props);
            hsqldb.start();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }
}
