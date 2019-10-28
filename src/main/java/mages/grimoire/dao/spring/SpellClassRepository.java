package mages.grimoire.dao.spring;

import java.util.List;
import mages.grimoire.model.SpellClass;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/** SpellClassRepository */
public interface SpellClassRepository extends Repository<SpellClass, Integer> {

  @Query("select * from spell_class where spell_id = :spellId")
  List<SpellClass> getSpellClasses(@Param("spellId") int spellId);

  @Modifying
  @Query("INSERT INTO spell_class (spell_id, type) VALUES (:spellId, :type)")
  void addSpellClass(@Param("spellId") int spellId, @Param("type") String type);
}
