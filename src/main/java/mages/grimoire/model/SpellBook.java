package mages.grimoire.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/** SpellBook */
@Data
public class SpellBook {

  @Id private int bookId;
  private String name;

  @Transient private List<Spell> spells = new LinkedList<>();
}
