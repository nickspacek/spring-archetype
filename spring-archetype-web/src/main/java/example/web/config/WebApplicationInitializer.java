package example.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class initializes the Spring MVC Servlet and Filters and allows us to
 * customize them.
 * 
 * Requires Servlet API 3.0 (Tomcat 7, Jetty 9)
 * 
 * @author Nick Spacek
 * 
 */
public class WebApplicationInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { WebSecurityConfigurer.class, WebAppConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfigurer.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
