package mhs.server;

import java.io.File;
import java.net.URL;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.http.fileupload.FileUtils;

/**
 * The server.
 */
public class Server {

    /**
     * The main method of the server.
     * 
     * @param args Arguments.
     * @throws Exception
     * @throws LifecycleException
     */
    public static void main(final String[] args) throws Exception {
        final Server server = new Server();
        server.startTomcat();
    }

    /**
     * Start the Tomcat server.
     * 
     * @throws Exception Exception.
     */
    private void startTomcat() throws Exception {

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

        // If a previous webapps directory exist, remove it to have a clean install
        final File webappsDir = new File("./webapps");
        if (webappsDir.exists()) {
            FileUtils.forceDelete(webappsDir);
        }

        // Create a webapps directory because Tomcat need it to unzip the webapp
        webappsDir.mkdir();

        // Start the server
        tomcat.setBaseDir("./");

        tomcat.start();
        tomcat.getServer().await();
    }
}
