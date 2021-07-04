package springTool;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import util.PropertyReaderUtil;

import javax.sql.DataSource;

public class SpringConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        PropertyReaderUtil.loadProperties();
        dataSource.setDriverClassName("com.informix.jdbc.IfxDriver");
        dataSource.setUrl(PropertyReaderUtil.getProperties().getProperty("URL"));
        dataSource.setUsername(PropertyReaderUtil.getProperties().getProperty("USER"));
        dataSource.setPassword(PropertyReaderUtil.getProperties().getProperty("PASS"));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return  new JdbcTemplate(dataSource());
    }
}
