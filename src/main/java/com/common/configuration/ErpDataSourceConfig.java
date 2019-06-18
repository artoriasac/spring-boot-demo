package com.common.configuration;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.mapper.erp",sqlSessionTemplateRef  = "erpSqlSessionTemplate")
public class ErpDataSourceConfig {
    @Bean(name = "erpData")
    @ConfigurationProperties(prefix = "spring.datasource.erp") // application.properteis中对应属性的前缀
    @Primary
    public DataSource erpData() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "erpSqlSessionFactory")
    @Primary
    public SqlSessionFactory erpSqlSessionFactory(@Qualifier("erpData") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/OmsSellRecordMapper.xml"));
        return bean.getObject();
    }

    @Bean(name = "erpTransactionManager")
    @Primary
    public DataSourceTransactionManager erpTransactionManager(@Qualifier("erpData") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "erpSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate erpSqlSessionTemplate(@Qualifier("erpSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
