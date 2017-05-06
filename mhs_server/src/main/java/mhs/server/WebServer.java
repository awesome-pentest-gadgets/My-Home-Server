/**
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
import java.util.logging.Logger;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 * @author stefv
 */
public class WebServer extends AbstractServer {

    /**
     * The logger.
     */
    private static final Logger LOG = Logger.getLogger("");

    /*
     * (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {

        // Start Tomcat
        try {
            startTomcat();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
    }

    /**
     * Start the Tomcat server.
     * 
     * @throws Exception Exception.
     */
    private void startTomcat() throws Exception {

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
        final File compileDir = new File("./tomcat." + Settings.getPort());
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
        tomcat.setPort(Settings.getPort());
        tomcat.start();
        tomcat.getServer().await();
    }
}
