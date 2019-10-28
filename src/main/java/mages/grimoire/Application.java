package mages.grimoire;

import com.fasterxml.jackson.databind.JsonNode;
import edu.byu.hbll.json.JsonUtils;
import edu.byu.hbll.json.ObjectMapperFactory;
import edu.byu.hbll.json.UncheckedObjectMapper;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Defines the entry point for the Spring Boot application and provides application context, such as
 * configuration, which can be injected into other Spring-managed classes.
 */
@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@EnableConfigurationProperties
@ConfigurationProperties
@EnableJdbcRepositories
public class Application {

  private static final UncheckedObjectMapper objectMapper = ObjectMapperFactory.newUnchecked();

  private JsonNode config;
  @Autowired DataSource datasource;

  /**
   * Launches this application.
   *
   * @param args the command line arguments provided at runtime
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  /**
   * Sets the application-specific configuration. This method will get called at deployment if there
   * is a top-level object called "config" in your Spring Boot configuration file(s).
   *
   * @param config the map containing raw config data parsed from the Spring Boot configuration
   */
  public void setConfig(Map<String, Object> config) {
    this.config = objectMapper.valueToTree(config);
    JsonUtils.fixArrays(this.config);
  }

  /**
   * Defines the Jackson ObjectMapper which should be used by Spring for JSON serialization and
   * deserialization (though it can also be used by other classes in lieu of recreating additional
   * instances).
   *
   * @return the object mapper
   */
  @Bean
  public static UncheckedObjectMapper objectMapper() {
    return objectMapper;
  }

  @PostConstruct
  public void postConstruct() throws Exception {
    Connection connection = datasource.getConnection();
    Statement statement = connection.createStatement();
    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`spell` ( `spell_id` INT NOT NULL AUTO_INCREMENT , `spell_name` VARCHAR(255) NOT NULL , `level` INT NOT NULL , `ritual` BOOLEAN NOT NULL , `casting_time` VARCHAR(255) NOT NULL , `range_value` INT NULL , `verbal` BOOLEAN NOT NULL , `somatic` BOOLEAN NOT NULL , `material` VARCHAR(255) NOT NULL , `school` VARCHAR(255) NOT NULL , `duration` INT NOT NULL , `concentration` BOOLEAN NOT NULL , `target` VARCHAR(255) NOT NULL , `saving_throw` TEXT NULL , `description` TEXT NOT NULL , `higher_levels` TEXT NULL , `damage` TEXT NULL , `damage_type` VARCHAR(255) NOT NULL , `book` VARCHAR(255) NOT NULL , UNIQUE `spell_id` (`spell_id`)) ENGINE = InnoDB;");
    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`user` ( `user_id` VARCHAR(255) NOT NULL, `name` VARCHAR(255) NOT NULL, UNIQUE `user_id` (`user_id`)) ENGINE = InnoDB;");
    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`spell_book` ( `book_id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(255) NOT NULL , UNIQUE `book_id` (`book_id`)) ENGINE = InnoDB;");

    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`user_spell_book` ( `book_id` INT NOT NULL , `user_id` VARCHAR(255) NOT NULL ) ENGINE = InnoDB;");
    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`spell_book_spell` ( `book_id` INT NOT NULL, `spell_id` INT NOT NULL ) ENGINE = InnoDB;");
    statement.execute(
        "CREATE TABLE IF NOT EXISTS `grimoire`.`spell_class` (`spell_id` INT NOT NULL, `type` VARCHAR(255) NOT NULL ) ENGINE = InnoDB;");
  }
}
