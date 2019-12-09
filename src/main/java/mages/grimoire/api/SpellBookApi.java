package mages.grimoire.api;

import java.security.Principal;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.spring.SpellRepository;
import mages.grimoire.dao.spring.UserRepository;
import mages.grimoire.model.Spell;
import mages.grimoire.model.SpellBook;
import mages.grimoire.model.User;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.representations.IDToken;
import org.springframework.http.ResponseEntity;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** SpellBookApi */
@RestController
@RequestMapping("/api/spellbooks")
@AllArgsConstructor
public class SpellBookApi {

  private final SpellRepository spells;
  private final UserRepository users;

  @GetMapping(path = "/{id}")
  public ResponseEntity<SpellBook> getSpellBook(@PathVariable("id") int id, Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User user = users.findById(userId).get();

    var bookopt = user.getBooks().stream().filter(book -> book.getBookId() == id).findFirst();
    if (bookopt.isPresent()) {
      return ResponseEntity.ok(bookopt.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<SpellBook> addSpellBook(
      @RequestParam("name") String name, Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User user = users.findById(userId).get();
    int nextId = user.getBooks().stream().map(book -> book.getBookId()).max(Comparators.comparable()).orElse(0) + 1;

    SpellBook book = new SpellBook();
    book.setName(name);
    book.setBookId(nextId);

    user.getBooks().add(book);
    users.save(user);

    return ResponseEntity.ok(book);
  }

  @PostMapping("/{bookId}")
  public ResponseEntity<SpellBook> addSpellToBook(
      @PathVariable("bookId") int bookId, @RequestParam("spellId") int spellId, Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User user = users.findById(userId).get();

    var bookopt = user.getBooks().stream().filter(book -> book.getBookId() == bookId).findFirst();
    if(bookopt.isEmpty()){
      return ResponseEntity.notFound().build();
    }
    SpellBook book = bookopt.get();

    var spellopt = spells.findById(spellId);
    if(spellopt.isEmpty()){
      return ResponseEntity.badRequest().build();
    }
    book.getSpells().add(spellopt.get());
    users.save(user);

    return ResponseEntity.ok(book);
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<?> deleteSpellBook(@PathVariable("bookId") int bookId, Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User user = users.findById(userId).get();
    var bookopt = user.getBooks().stream().filter(book -> book.getBookId() == bookId).findFirst();
    if(bookopt.isEmpty()){
      return ResponseEntity.badRequest().build();
    }
    SpellBook book = bookopt.get();
    user.getBooks().remove(book);
    users.save(user);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{bookId}/{spellId}")
  public ResponseEntity<?> removeSpellFromBook(
      @PathVariable("bookId") int bookId, @PathVariable("spellId") int spellId, Principal principal) {
    KeycloakPrincipal<RefreshableKeycloakSecurityContext> kcp = (KeycloakPrincipal) principal;
    IDToken idToken = kcp.getKeycloakSecurityContext().getIdToken();
    String userId = idToken.getSubject();
    User user = users.findById(userId).get();
    var bookopt = user.getBooks().stream().filter(book -> book.getBookId() == bookId).findFirst();
    if(bookopt.isEmpty()){
      return ResponseEntity.badRequest().build();
    }
    SpellBook book = bookopt.get();

    var spellopt = book.getSpells().stream().filter(spell -> spell.getSpellId() == spellId).findFirst();
    if(spellopt.isEmpty()){
      return ResponseEntity.badRequest().build();
    }
    Spell spell = spellopt.get();
    book.getSpells().remove(spell);
    users.save(user);

    return ResponseEntity.ok().build();
  }
}
