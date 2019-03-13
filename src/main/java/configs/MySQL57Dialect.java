package main.java.configs;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.IntegerType;

public class MySQL57Dialect extends org.hibernate.dialect.MySQL57Dialect {
	
   public MySQL57Dialect() {
       super();
       registerFunction("bitwise_and", new SQLFunctionTemplate(IntegerType.INSTANCE, "?1 & ?2"));
   }
}
