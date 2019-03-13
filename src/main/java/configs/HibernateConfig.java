package stdio.configs;
import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration 
@EnableTransactionManagement
 @PropertySource("classpath:development.properties")
//@PropertySource("classpath:production.properties")
public class HibernateConfig {

	@Autowired
    private Environment env;	
	
	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}
	
 	@Bean
	public SessionFactory sessionFactory() {
	  LocalSessionFactoryBean sFactory = new LocalSessionFactoryBean();
	  sFactory.setDataSource(getDataSource());
	  sFactory.setPackagesToScan("stdio.entities");
	  sFactory.setHibernateProperties(hibernateProperties());
	  sFactory.setPhysicalNamingStrategy(new PhysicalNamingStrategyImpl());
	  
	  try {
		  sFactory.afterPropertiesSet();
	  } catch (IOException e) {
	     e.printStackTrace();
	  }
	  return sFactory.getObject();
	}
 	
    @Bean
	public DataSource getDataSource() {
	     BasicDataSource dataSource = new BasicDataSource();
	     dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
	     dataSource.setUrl(env.getProperty("database.url"));
	     dataSource.setUsername(env.getProperty("database.username"));
	     dataSource.setPassword(env.getProperty("database.password"));
	     
	     return dataSource;
	}
	
    @Bean
	public HibernateTransactionManager hibernateTransactionManager() {
	     return new HibernateTransactionManager(sessionFactory());
	}
    
    private Properties hibernateProperties() {
         Properties properties = new Properties();
         properties.put(AvailableSettings.DIALECT, env.getProperty("hibernate.dialect"));
         properties.put(AvailableSettings.SHOW_SQL, env.getProperty("hibernate.show_sql"));
         properties.put(AvailableSettings.FORMAT_SQL, env.getProperty("hibernate.format_sql"));
         
         properties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hibernate.hbm2dll_auto"));
         properties.put(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "false");

         return properties;        
    }	
} 