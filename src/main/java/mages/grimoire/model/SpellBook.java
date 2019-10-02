package mages.grimoire.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

/** SpellBook */
@Data
public class SpellBook {

  @Id private int bookId;
  private int spellId;
  private List<Spell> spells = new LinkedList<>();
}
