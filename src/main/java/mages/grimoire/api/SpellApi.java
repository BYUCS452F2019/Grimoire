package mages.grimoire.api;

import lombok.AllArgsConstructor;
import mages.grimoire.dao.SpellRepository;
import mages.grimoire.model.Spell;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** SpellApi */
@RestController
@RequestMapping("/spells")
@AllArgsConstructor
public class SpellApi {

  private final SpellRepository spells;

  @GetMapping
  public ResponseEntity<Iterable<Spell>> getAllSpells() {
    return ResponseEntity.ok(spells.findAll());
  }
}
