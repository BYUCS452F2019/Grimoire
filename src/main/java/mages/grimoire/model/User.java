package mages.grimoire.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

/** User */
@Data
public class User {

  @Id private String userId;
  private String name;
  @Transient private List<SpellBook> books = new LinkedList<>();
}
