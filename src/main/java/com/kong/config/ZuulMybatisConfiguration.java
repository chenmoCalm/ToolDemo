//package com.kong.config;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.mapper.MapperScannerConfigurer;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.core.io.support.ResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.util.StringUtils;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//public class ZuulMybatisConfiguration implements ApplicationContextAware {
//
//	protected Log logger = LogFactory.getLog(this.getClass());
//	@Value("${datasources.mybatis.dialect}")
//	private String dialect;
//	@Value("${datasources.mybatis.aliases-package}")
//	private String aliasesPackage;
//	@Value("${datasources.mybatis.mapper-locations}")
//	private String mapperLocations;
//	@Value("${datasources.mybatis.underscore-to-camel-case}")
//	private Boolean underscoreToCamelCase;
//
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//	}
//
//	@Bean(name = "zuulDataSource")
//	@ConfigurationProperties(prefix = "datasources.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build(); // .type(DataSource.class).build();
//	}
//
//	@Bean(name = "zuulSqlSessionFactory")
//	public SqlSessionFactory sqlSessionFactoryBean() {
//		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//		sqlSessionFactoryBean.setDataSource(dataSource());
//		sqlSessionFactoryBean.setTypeAliasesPackage(aliasesPackage);
//		sqlSessionFactoryBean.setDialect(dialect);
//		//添加XML目录
//		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//		try {
//			if (!StringUtils.isEmpty(mapperLocations)) {
//				String[] paths = mapperLocations.split(",");
//				Resource[] resources = null;
//				for (String path : paths) {
//					resources = (Resource[]) ArrayUtils.addAll(resources, resolver.getResources(path));
//				}
//				Arrays.sort(resources, new ResourceComparator());
//				sqlSessionFactoryBean.setMapperLocations(resources);
//			}
//			sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(underscoreToCamelCase);
//			return sqlSessionFactoryBean.getObject();
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	@Bean(name = "zuulSqlSessionTemplate")
//	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//		return new SqlSessionTemplate(sqlSessionFactory);
//	}
//
//	@Bean(name = "zuulTransactionManager")
//	public PlatformTransactionManager annotationDrivenTransactionManager() {
//		return new DataSourceTransactionManager(dataSource());
//	}
//
//	@Configuration
//	@AutoConfigureAfter(ZuulMybatisConfiguration.class)
//	public static class MyBatisMapperScannerConfig implements ApplicationContextAware {
//
//		private String basePackage;
//		private Boolean underscoreToCamelCase;
//
//		@Override
//		public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//			basePackage = applicationContext.getEnvironment().getProperty("datasources.zuul.mybatis.base-package");
//			underscoreToCamelCase = Boolean.parseBoolean(applicationContext.getEnvironment().getProperty("datasources.zuul.mybatis.underscore-to-camel-case"));
//		}
//
//		@Bean(name = "zuulMapperScannerConfigurer")
//		public MapperScannerConfigurer mapperScannerConfigurer() {
//			MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//			mapperScannerConfigurer.setCamelhumpToUnderline(underscoreToCamelCase);
//			mapperScannerConfigurer.setBasePackage(basePackage);
//			mapperScannerConfigurer.setSqlSessionFactoryBeanName("zuulSqlSessionFactory");
//			return mapperScannerConfigurer;
//		}
//	}
//
//	public class ResourceComparator implements Comparator<Resource> {
//
//		@Override
//		public int compare(Resource r1, Resource r2) {
//			if (r1.getFilename().equals("AbstractMapper.xml")) {
//				return -1;
//			} else if (r2.getFilename().equals("AbstractMapper.xml")) {
//				return 1;
//			}
//			return 0;
//		}
//	}
//}