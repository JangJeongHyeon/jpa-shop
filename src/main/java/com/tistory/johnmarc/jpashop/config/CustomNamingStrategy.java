package com.tistory.johnmarc.jpashop.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.factory.annotation.Value;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {
    @Value("${database.prefix.enable}")
    private boolean isPrefixed = true;
    @Value("${database.prefix.name:jpa_shop}")
    private String PREFIX = "jpa_";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        if(isPrefixed){
        Identifier prefixedTableName = new Identifier(PREFIX + name.getText().toLowerCase(), name.isQuoted());
        return super.toPhysicalTableName(prefixedTableName, context);
        }
        return super.toPhysicalTableName(name, context);
    }
}
