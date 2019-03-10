package main.java.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
								RootConfig.class,
//								HibernateConfig.class,
//								SecurityConfig.class
							};
	}
	
	@Override
    protected Class<?>[] getServletConfigClasses() {
		return new Class[] {
				WebMvcConfig.class
		};
    }
	
    @Override
    protected String[] getServletMappings() {
    	return new String[]{ "/" };
    }
    
    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { };
    }
}