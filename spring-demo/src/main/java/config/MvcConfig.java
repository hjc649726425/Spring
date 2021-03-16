package config;

import com.hjc.event.MyApplicationEvent;
import com.hjc.service.ServiceTest;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.hjc")
@EnableWebMvc
@EnableAspectJAutoProxy
@EnableTransactionManagement(proxyTargetClass = false, mode = AdviceMode.PROXY) //开启事务
@MapperScan("com.hjc.dao")
public class MvcConfig {

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


	//处理文件上传
	//如果未引入multipartResolver bean，spring默认为 null，就不能进行文件上传了
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		resolver.setResolveLazily(true);
		resolver.setMaxInMemorySize(40960);
		//允许上传文件最大为1G
		resolver.setMaxUploadSize(1024 * 1024 * 1024);
		return resolver;
	}

	/**
	 * springboot 是先启动 spring 容器，然后由spring容器启动tomcat启动
	 * dispatcherServlet
	 * @param webApplicationContext
	 * @return
	 */
	@Bean("dispatcherServlet")
	public DispatcherServlet dispatcherServlet(WebApplicationContext webApplicationContext) {
		return new DispatcherServlet(webApplicationContext);
	}

	/**
	 * 这是个事件监听器
	 */
	@EventListener(MyApplicationEvent.class)
	public void listener(MyApplicationEvent event) {
		System.out.println("@EventListener监听到了事件："
				+ Thread.currentThread().getName() + " | " + event.getSource());
	}

	@Bean(name = {"serviceTest"}, initMethod = "init")
	public ServiceTest s2(){
		return new ServiceTest("t2");
	}
}
