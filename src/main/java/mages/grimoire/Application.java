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
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
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
@EnableMongoRepositories
public class Application {

  private static final UncheckedObjectMapper objectMapper = ObjectMapperFactory.newUnchecked();

  private JsonNode config;

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

}
