package config;

import com.hjc.model.User;
import com.hjc.process.MyConditional;
import com.hjc.process.MyImportBeanDefinitionRegistrar;
import com.hjc.service.ServiceTest;
import com.hjc.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.hjc",
		excludeFilters =@ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.hjc.process.mvc.*"})
)
//@ImportResource("spring.xml")
@Import(
		{User.class, MyImportBeanDefinitionRegistrar.class
		})  //导入组件
@Conditional(value = { MyConditional.class })
//开启AOP
//proxyTargetClass true 必须要cglib代理 默认false
//exposeProxy true 把proxy动态代理对象设置到AopContext上下文中
@EnableAspectJAutoProxy(proxyTargetClass = false, exposeProxy = false)
@EnableTransactionManagement(proxyTargetClass = false, mode = AdviceMode.PROXY) //开启事务
@MapperScan("com.hjc.dao")
//开启异步化支持
@EnableAsync
public class Appconfig {

	private String driverClass = "com.mysql.cj.jdbc.Driver";
	private String username="root";
	private String password="root123456";
	private String url="jdbc:mysql://localhost:3306/user?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

	//注册数据源
	@Bean
	public DataSource getDataSorce() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	/**
	 * 获取sessionFactory
	 */
	@Bean
	public SqlSessionFactory getSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getDataSorce());
		return sqlSessionFactoryBean.getObject();
	}
	//注册jdbc事务管理器
	@Bean
	public PlatformTransactionManager getTrans() throws Exception {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(getDataSorce());
		return  transactionManager;
	}

	@Bean
	public User getUser() throws Exception {
		getSqlSessionFactory();
		return new User();
	}

	@Bean("testService")
	public ServiceTest s1(){
		s2();
		return new ServiceTest("t1");
	}

	@Bean(name = {"serviceTest"}, initMethod = "init")
	public ServiceTest s2(){
		return new ServiceTest("t2");
	}

	@Bean(name = {"userService1"})
	public UserService u1(){
		UserService userService = new UserService();
		userService.setTestService(new ServiceTest("333"));
		return userService;
	}

}
