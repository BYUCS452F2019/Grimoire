package mages.grimoire.api;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.SpellDao;
import mages.grimoire.model.Spell;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** SpellApi */
@RestController
@RequestMapping("/api/spells")
@AllArgsConstructor
public class SpellApi {

  private final SpellDao spells;

  @GetMapping
  public ResponseEntity<Stream<Spell>> getAllSpells() {
    return ResponseEntity.ok(spells.getAllSpells());
  }

  @PostMapping
  public ResponseEntity<Spell> addSpell(@RequestBody Spell spell) {
    spells.addSpell(spell);
    return ResponseEntity.ok(spell);
  }
}
