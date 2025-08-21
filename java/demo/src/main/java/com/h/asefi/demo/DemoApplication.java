package com.h.asefi.demo;

import com.h.asefi.demo.common.DatabaseCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableCaching
@EnableAspectJAutoProxy
public class DemoApplication {

	private final DatabaseCreator databaseCreator;

	@Value("${spring.datasource.url}")
	private String appJdbcUrl;

	@Value("${spring.datasource.username}")
	private String appDbUser;

	@Value("${spring.datasource.password}")
	private String appDbPassword;

    public DemoApplication(DatabaseCreator databaseCreator) {
        this.databaseCreator = databaseCreator;
    }

	/**
	 * Configures and provides a DataSource bean for the application.
	 * This method first ensures that the database and schema are initialized
	 * using the DatabaseCreator before creating and returning a DataSource
	 * based on the application properties.
	 *
	 * @return DataSource configured with application properties.
	 */
	@Bean
	public DataSource dataSource() {
		// Call the DatabaseCreator to initialize the DB first
		databaseCreator.initializeDatabaseRequirements(); // Initialize DB and Schema

		// Now, create DataSource after DB initialization
		return DataSourceBuilder.create()
				.url(appJdbcUrl)
				.username(appDbUser)
				.password(appDbPassword)
				.build();
	}

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
