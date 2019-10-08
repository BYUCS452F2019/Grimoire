package mages.grimoire.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/** SpellBook */
@Data
public class DatabaseSpellBookSpell {

  @Id private int bookId;
  private int spellId;
}
