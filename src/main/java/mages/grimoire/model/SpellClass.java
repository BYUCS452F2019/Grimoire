package mages.grimoire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** SpellClass */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpellClass {

  private int spellId;
  private ClassType type;
}
