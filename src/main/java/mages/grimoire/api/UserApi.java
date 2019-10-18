package mages.grimoire.api;

import java.security.Principal;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.UserDao;
import mages.grimoire.model.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** UserApi */
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApi {

  private UserDao users;

  @CrossOrigin(origins = "*", allowCredentials = "true")
  @GetMapping
  public ResponseEntity<User> getUser(Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User grimoireUser =
        users
            .getUser(userId)
            .orElseGet(
                () -> {
                  User user = new User();
                  user.setUserId(userId);
                  user.setName(idToken.getName());
                  return users.addUser(user);
                });

    return ResponseEntity.ok(grimoireUser);
  }
}
