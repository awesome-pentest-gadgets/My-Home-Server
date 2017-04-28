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
package mhs.core;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;

/**
 * @author svanp
 * @version $Id$
 */
public class Server {

    /**
     * @param args
     * @throws ServletException
     * @throws LifecycleException
     */
    public static void main(final String[] args) throws ServletException, LifecycleException {

        String path = System.getProperty("user.dir");
        System.out.println(path);

        // Tomcat server = new Tomcat();
        // server.setPort(8080);
        // server.setBaseDir(System.getProperty("java.io.tmpdir"));
        // server.addWebapp("/mhs", "/tmp/test.war");
        // server.start();
        //
        // server.getServer().await();
    }

}
