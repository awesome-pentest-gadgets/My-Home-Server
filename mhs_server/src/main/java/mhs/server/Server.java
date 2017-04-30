package mhs.server;

import java.io.File;
import java.net.URL;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
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

        final Class<?> serverClass = Server.class;
        final URL location = serverClass.getResource('/' + serverClass.getName().replace('.', '/') + ".class");
        if (!"jar".equals(location.getProtocol())) {
            throw new RuntimeException("The file is not a JAR or WAR.");
        }
        final File warFile = new File(location.getFile().split("!")[0]);
        final String webappDirLocation = warFile.getName();

        // If a previous webapps directory exist, remove it to have a clean install
        final File webappsDir = new File("./webapps");
        if (webappsDir.exists()) {
            FileUtils.forceDelete(webappsDir);
        }

        // Create a webapps directory because Tomcat need it to unzip the webapp
        webappsDir.mkdir();

        // Start the server
        final Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("./");
        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
