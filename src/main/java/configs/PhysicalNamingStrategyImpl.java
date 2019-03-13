package stdio.configs;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl {
	
	private static final long serialVersionUID = 1L;
	private static final String TABLE_PREFIX = "se_";
	
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {    	
        return new Identifier(TABLE_PREFIX + camelToUnderscore(name.getText()).toLowerCase(), name.isQuoted());
    }
    
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        return new Identifier(camelToUnderscore(name.getText()).toLowerCase(), name.isQuoted());
    }
    
    protected static String camelToUnderscore(String name) {
    	return name.replaceAll("([^_A-Z])([A-Z])", "$1_$2");
    }
}