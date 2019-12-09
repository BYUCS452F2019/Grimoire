package mages.grimoire.model;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** User */
@Data
@Document
public class User {

  @Id private String userId;
  private String name;
  private List<SpellBook> books = new LinkedList<>();
}
