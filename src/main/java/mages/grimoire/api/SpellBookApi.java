package mages.grimoire.api;

import java.security.Principal;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.SpellBookDao;
import mages.grimoire.dao.SpellDao;
import mages.grimoire.model.SpellBook;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.ResponseEntity;
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

  private final SpellBookDao spellBooks;
  private final SpellDao spells;

  @GetMapping(path = "/{id}")
  public ResponseEntity<SpellBook> getSpellBook(@PathVariable("id") int id) {
    Optional<SpellBook> result = spellBooks.getSpellBook(id);
    if (result.isPresent()) {
      return ResponseEntity.ok(result.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<SpellBook> addSpellBook(
      @RequestParam("name") String name, Principal principal) {
    SpellBook book = new SpellBook();
    book.setName(name);

    KeycloakPrincipal kcp = (KeycloakPrincipal) principal;
    String userId = kcp.getKeycloakSecurityContext().getIdToken().getSubject();

    spellBooks.saveSpellBook(book);

    spellBooks.addBookToUser(book.getBookId(), userId);

    return ResponseEntity.ok(book);
  }

  @PostMapping("/{bookId}")
  public ResponseEntity<SpellBook> addSpellToBook(
      @PathVariable("bookId") int bookId, @RequestParam("spellId") int spellId) {
    var bookOpt = spellBooks.getSpellBook(bookId);
    if (bookOpt.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    var spellOpt = spells.getSpell(spellId);
    if (spellOpt.isEmpty()) {
      return ResponseEntity.badRequest().build();
    }

    spellBooks.addSpellToBook(bookId, spellId);

    return ResponseEntity.ok(spellBooks.getSpellBook(bookId).get());
  }

  @DeleteMapping("/{bookId}")
  public ResponseEntity<SpellBook> deleteSpellBook(@PathVariable("bookId") int bookId) {
    spellBooks.deleteSpellBook(bookId);
    return ResponseEntity.ok().build();
  }
}
