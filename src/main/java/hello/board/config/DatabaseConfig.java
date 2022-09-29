package hello.board.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;


@Configuration
//이 클래스를 자바 기반의 설정 파일로 인식
@PropertySource("classpath:/application.properties")
//해당 클래스에서 참조할 properties 파일의 위치를 지정
public class DatabaseConfig {

    @Autowired
    //빈으로 등록된 객체를 클래스에 주입하는데 사용
    private ApplicationContext context;
    //스프링 컨테이너중 하나 빈의 생성과 사용, 관계, 생명 주기등 관리
    //빈을 쉽게 이야기하면 객체 100개의 클래스 의존적인 문제가 많으면 "결합도가 높다" 이러한 문제를 컨테이너에서 빈을 주입
    @Bean
    //@Configuration 클래스에서만 지정가능
    //@Bean이 지정된 객체는 컨테이너에 의해 관리되는 빈 등록
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    //@PropertySource에 지정한 곳에서 spring.datasource.hikari로 시작하는 설정을 읽어 메서드에 매핑(바인딩) 한다
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }
    //히카리 객체 생성 커넥션풀 사용
    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }
    //데이터소스객체 생성 커넥션풀 사용 미리 생성해둔 커넥션 제공하고 다시 돌려받는다
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }
    //sqlSessionFactoryBean은 마이바티스와 스프링 연동
    //마이바티스 xml Mapper, 설정 파일 위치 등 지정

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}
