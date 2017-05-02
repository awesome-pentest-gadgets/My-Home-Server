/**
 * Copyright 2008-2016 stefv
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
import java.net.URL;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 * The server.
 * 
 * @author stefv
 */
public class Server {

    /**
     * Default port.
     */
    private static final int PORT = 8080;

    /**
     * Port of the server.
     */
    private int port = PORT;

    /**
     * Log file.
     */
    private String logFile = null;

    /**
     * The main method of the server.
     * 
     * @param args Arguments.
     * @throws Exception
     */
    public static void main(final String[] args) throws Exception {
        final Server server = new Server();
        server.startTomcat(args);
    }

    /**
     * Parse the arguments.
     * 
     * @param args The arguments.
     */
    private void parseArguments(final String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--port=")) {
                port = Integer.parseInt(arg.substring("--port=".length()).trim());
            }
            if (arg.startsWith("--log=")) {
                logFile = arg.substring("--log=".length()).trim();
            }
        }
    }

    /**
     * Start the Tomcat server.
     * 
     * @param args Arguments.
     * @throws Exception Exception.
     */
    private void startTomcat(final String[] args) throws Exception {

        // Parse the arguments
        parseArguments(args);

        // Prepare the logs
        if (logFile != null) {
            final Logger logger = Logger.getLogger("");
            final Handler fileHandler = new FileHandler(logFile, true);
            fileHandler.setFormatter(new SimpleFormatter());
            fileHandler.setLevel(Level.INFO);
            fileHandler.setEncoding("UTF-8");
            logger.addHandler(fileHandler);
        }

        // Prepare the HTTP server
        final Tomcat tomcat = new Tomcat();
        final String webappDirLocation;
        final Class<?> serverClass = Server.class;
        final URL location = serverClass.getResource('/' + serverClass.getName().replace('.', '/') + ".class");

        final File warFile = new File(location.getFile().split("!")[0]);
        if (warFile.getName().endsWith(".war")) {
            webappDirLocation = warFile.getName();
            tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        } else if (warFile.getName().endsWith(".class")) {
            webappDirLocation = "src/main/webapp/";

            final StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

            final File additionWebInfClasses = new File("target/classes");
            final WebResourceRoot resources = new StandardRoot(ctx);
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/"));
            ctx.setResources(resources);

        } else {
            throw new RuntimeException("Not a valid archive to start the embedded server.");
        }

        // Remove the compile directory
        final File compileDir = new File("./tomcat." + port);
        if (compileDir.exists()) {
            FileUtils.forceDelete(compileDir);
        }

        // Create a compile directory
        compileDir.mkdir();

        // If a previous webapps directory exist, remove it to have a clean
        // install
        final File webappsDir = new File(compileDir, "./webapps");
        if (webappsDir.exists()) {
            FileUtils.forceDelete(webappsDir);
        }

        // Create a webapps directory because Tomcat need it to unzip the webapp
        webappsDir.mkdir();

        // Start the server
        tomcat.setBaseDir("./");
        tomcat.setPort(port);
        tomcat.start();
        tomcat.getServer().await();
    }
}
