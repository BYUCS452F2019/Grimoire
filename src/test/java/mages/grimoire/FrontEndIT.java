package mages.grimoire;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import mages.grimoire.Application;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

/** Integration tests for the front end. */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FrontEndIT {

  @LocalServerPort int port;

  /** Verifies the frontend index page is working. */
  @Test
  public void testIndex() {
    RestTemplate rest = new RestTemplate();
    String uri = "http://localhost:" + port + "/grimoire";
    ResponseEntity<String> response = rest.getForEntity(uri, String.class);

    // Verify that the correct response code was sent.
    assertEquals(HttpStatus.OK, response.getStatusCode());

    // Verify that the index was sent.
    String body = response.getBody();
    assertTrue(body.length() > 0);
  }
}
