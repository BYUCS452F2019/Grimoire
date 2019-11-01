package mages.grimoire.dao.spring;

import java.util.List;
import mages.grimoire.model.DatabaseSpellBookSpell;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/** SpellBookRepository */
public interface SpellBookSpellRepository extends Repository<DatabaseSpellBookSpell, Integer> {

  @Query("select * from spell_book_spell where book_id = :bookId")
  List<DatabaseSpellBookSpell> findByBookId(@Param("bookId") int bookId);

  @Modifying
  @Query("insert into spell_book_spell (book_id, spell_id) values (:bookId, :spellId)")
  void addSpellToBook(@Param("bookId") int bookId, @Param("spellId") int spellId);

  @Modifying
  @Query("delete from spell_book_spell where book_id = :bookId and spell_id = :spellId")
  void removeSpellFromBook(@Param("bookId") int bookId, @Param("spellId") int spellId);

  @Modifying
  @Query("delete from spell_book_spell where book_id = :bookId")
  void deleteBook(@Param("bookId") int bookId);
}
