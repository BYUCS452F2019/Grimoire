package mages.grimoire.dao;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.spring.SpellClassRepository;
import mages.grimoire.dao.spring.SpellRepository;
import mages.grimoire.model.ClassType;
import mages.grimoire.model.Spell;
import org.springframework.stereotype.Component;

/** SpellDao */
@Component
@AllArgsConstructor
public class SpellDao {

  private SpellRepository spells;
  private SpellClassRepository spellClasses;

  public Optional<Spell> getSpell(int id) {
    var result = spells.findById(id);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    Spell spell = result.get();
    var enrich =
        spellClasses.getSpellClasses(id).stream()
            .map(obj -> obj.getType())
            .collect(Collectors.toList());
    spell.setClassType(enrich);
    return Optional.of(spell);
  }

  public Stream<Spell> getAllSpells() {
    return StreamSupport.stream(spells.findAll().spliterator(), false)
        .peek(
            spell -> {
              spell.setClassType(
                  spellClasses.getSpellClasses(spell.getSpellId()).stream()
                      .map(obj -> obj.getType())
                      .collect(Collectors.toList()));
            });
  }

  public void addSpell(Spell spell) {
    spells.save(spell);
    for (ClassType type : spell.getClassType()) {
      spellClasses.addSpellClass(spell.getSpellId(), type.toString());
    }
  }
}
