package example.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This class initializes the Spring Security Servlet Filters and allows us to
 * customize them.
 * 
 * Requires Servlet API 3.0 (Tomcat 7, Jetty 9)
 * 
 * @author Nick Spacek
 * 
 */
public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {

}
