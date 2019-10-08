package mages.grimoire.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/** UserSpellBook */
@Data
public class UserSpellBook {

  @Id private String user_id;
  private int bookId;
}
