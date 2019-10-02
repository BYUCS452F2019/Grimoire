package mages.grimoire.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/** Spell */
@Data
public class Spell {

  @Id private int spellId;
  private String spellName;
  private int level;
  private boolean ritual;
}
