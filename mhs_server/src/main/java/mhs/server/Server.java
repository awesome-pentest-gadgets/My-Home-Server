package mhs.server;

import java.io.File;

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
     * @throws Exception
     * @throws LifecycleException
     */
    public static void main(final String[] args) throws Exception {
        final Server server = new Server();
        server.startTomcat();
    }

    private void startTomcat() throws ServletException, LifecycleException {
        final String webappDirLocation = "./mhs_server.war";
        final Tomcat tomcat = new Tomcat();

        new File("./webapps").mkdir();

        tomcat.setBaseDir("./");
        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());

        tomcat.start();
        tomcat.getServer().await();
    }

    // System.out.println("User dir: " + System.getProperty("user.dir"));
    // System.out.println("Base dir: " + System.getProperty("java.io.tmpdir"));

    // final String webappDirLocation = "src/main/webapp/";
    // The port that we should run on can be set into an environment variable
    // Look for that variable and default to 8080 if it isn't there.
    // String webPort = System.getenv("PORT");
    // if (webPort == null || webPort.isEmpty()) {
    // webPort = "8080";
    // }
    //
    // tomcat.setPort(Integer.valueOf(webPort));

    /*
     * final StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
     * System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath()); // Declare an
     * alternative location for your "WEB-INF/classes" dir // Servlet 3.0 annotation will work final File additionWebInfClasses = new
     * File("target/classes"); final WebResourceRoot resources = new StandardRoot(ctx); resources.addPreResources(new
     * DirResourceSet(resources, "/WEB-INF/classes", additionWebInfClasses.getAbsolutePath(), "/")); ctx.setResources(resources);
     */

    // final URL location = Server.class.getResource('/' + Server.class.getName().replace('.', '/') + ".class");
    // Server.class.getResource(name)

}
