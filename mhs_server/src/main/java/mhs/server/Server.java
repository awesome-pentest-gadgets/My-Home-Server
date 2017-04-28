package mhs.server;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * The server.
 */
public class Server {

    /**
     * The main method of the server.
     * 
     * @param args Arguments.
     * @throws ServletException
     * @throws LifecycleException
     */
    public static void main(final String[] args) throws ServletException, LifecycleException {

        System.out.println("User dir: " + System.getProperty("user.dir"));
        System.out.println("Base dir: " + System.getProperty("java.io.tmpdir"));

        final Tomcat server = new Tomcat();
        server.setPort(8080);
        server.setBaseDir(System.getProperty("user.dir"));
        server.addWebapp("", "mhs_server.war");
        server.start();

        server.getServer().await();
    }
}
