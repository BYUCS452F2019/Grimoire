package mages.grimoire.api;

import lombok.AllArgsConstructor;
import mages.grimoire.dao.spring.SpellRepository;
import mages.grimoire.model.Spell;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.StreamSupport;

/** SpellApi */
@RestController
@RequestMapping("/api/spells")
@AllArgsConstructor
public class SpellApi {

  private final SpellRepository spells;

  @GetMapping
  public ResponseEntity<Iterable<Spell>> getAllSpells() {
    return ResponseEntity.ok(spells.findAll());
  }

  @PostMapping
  public ResponseEntity<Spell> addSpell(@RequestBody Spell spell) {
    int nextId = StreamSupport.stream(spells.findAll().spliterator(), false).map(savedspell -> savedspell.getSpellId()).max(Integer::compareTo).orElse(0) + 1;
    spell.setSpellId(nextId);
    spells.save(spell);
    return ResponseEntity.ok(spell);
  }
}
