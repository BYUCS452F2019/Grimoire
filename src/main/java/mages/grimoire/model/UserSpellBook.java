package mages.grimoire.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;

/** UserSpellBook */
@Data
public class UserSpellBook {

  @Id private String username;
  private int bookId;
  private List<SpellBook> spellBooks = new LinkedList<>();
}
