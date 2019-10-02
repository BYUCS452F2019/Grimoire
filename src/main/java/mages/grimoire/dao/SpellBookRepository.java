package mages.grimoire.dao;

import java.util.List;
import mages.grimoire.model.SpellBook;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/** SpellBookRepository */
public interface SpellBookRepository extends Repository<SpellBook, Integer> {

  @Query("select * from spellbook where bookId = :bookId")
  List<SpellBook> findByBookId(@Param("bookId") int bookId);

  @Modifying
  @Query("insert into spellbook (bookId, spellId) values (:bookId, :spellId)")
  void addSpellToBook(@Param("bookId") int bookId, @Param("spellId") int spellId);

  @Modifying
  @Query("delete from spellbook where bookId = :bookId and spellId = :spellId")
  void removeSpellFromBook(@Param("bookId") int bookId, @Param("spellId") int spellId);
}
