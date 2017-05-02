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
package mhs.server.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mhs.server.Settings;

/**
 * A filter to log the accesses to the webapp.
 * 
 * @author stefv
 */
@WebFilter(filterName = "AccessLogFilter", urlPatterns = { "/*" })
public class AccessLogFilter implements Filter {

    /**
     * Date format.
     */
    private static final SimpleDateFormat DDMMMYYYY_HHMMSS = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss z");

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {

        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        chain.doFilter(request, response);

        // If the access log file is set, append the file
        if (Settings.getAccessLog() != null) {
            final String message = createMessage(httpServletRequest, httpServletResponse);
            logMessage(message);
        }
    }

    /**
     * Log the message.
     * 
     * @param message The message.
     */
    private void logMessage(final String message) {
        try {
            final Path path = Settings.getAccessLog().toPath();
            Files.write(path, (message + System.getProperty("line.separator")).getBytes(), StandardOpenOption.APPEND,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.SYNC);
        } catch (IOException e) {
        }
    }

    /**
     * Create the message to log.
     * 
     * @param httpServletRequest The request.
     * @param httpServletResponse The response.
     * @return The message.
     */
    private String createMessage(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse) {

        final StringBuilder message = new StringBuilder();
        message.append(httpServletRequest.getRemoteAddr());
        message.append(" - ");
        if (httpServletRequest.getRemoteUser() != null) {
            message.append(httpServletRequest.getRemoteUser());
        } else {
            message.append("-");
        }
        message.append(" ");
        message.append("[" + DDMMMYYYY_HHMMSS.format(new Date()) + "]");
        message.append(" ");
        message.append("\"");
        message.append(httpServletRequest.getMethod());
        message.append(" ");
        message.append(httpServletRequest.getRequestURI());
        message.append(" ");
        message.append(httpServletRequest.getProtocol());
        message.append("\" ");
        message.append(httpServletResponse.getStatus());
        message.append(" ");
        message.append("0"); // FIXME Put the size of the content

        return message.toString();
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }
}
