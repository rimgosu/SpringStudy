package kr.spring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = {"kr.spring.mapper"})
@PropertySource({"classpath:persistence-mysql.properties"})
public class RootConfig {
	
	// org.springframework.core.env.Environment
	// 내가 만든 properties 파일을 읽어오는 객체
	@Autowired
	Environment env;
	
	
	// root-context.xml을 대체할 클래스
	// javax.sql.DataSource
	public DataSource myDataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(env.getProperty("jdbc.driver"));
		hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
		hikariConfig.setUsername(env.getProperty("jdbc.user"));
		hikariConfig.setPassword(env.getProperty("jdbc.password"));
		
		HikariDataSource myDataSource = new HikariDataSource(hikariConfig);
		return myDataSource;
	}
	
	@Bean
	public SqlSessionFactory sessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(myDataSource());
		return (SqlSessionFactory) sessionFactory.getObject();
		
	}
	
}
