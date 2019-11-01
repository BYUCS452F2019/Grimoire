package mages.grimoire.dao.spring;

import java.util.List;
import mages.grimoire.model.UserSpellBook;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

/** UserSpellBookRepository */
public interface UserSpellBookRepository extends Repository<UserSpellBook, Integer> {

  @Query("select * from user_spell_book where user_id = :userId")
  List<UserSpellBook> getUsersBooks(@Param("userId") String userId);

  @Modifying
  @Query("INSERT INTO user_spell_book (book_id, user_id) VALUES ( :bookId, :userId )")
  void addBookToUser(@Param("bookId") int bookId, @Param("userId") String userId);

  @Modifying
  @Query("DELETE FROM user_spell_book WHERE book_id = :bookId")
  void deleteBook(@Param("bookId") int bookId);
}
