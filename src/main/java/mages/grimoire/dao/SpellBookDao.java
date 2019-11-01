package mages.grimoire.dao;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.spring.SpellBookRepository;
import mages.grimoire.dao.spring.SpellBookSpellRepository;
import mages.grimoire.dao.spring.UserSpellBookRepository;
import mages.grimoire.model.DatabaseSpellBookSpell;
import mages.grimoire.model.SpellBook;
import org.springframework.stereotype.Component;

/** SpellBookDao */
@Component
@AllArgsConstructor
public class SpellBookDao {

  private final SpellBookSpellRepository spellBookSpells;
  private final SpellBookRepository spellBooks;
  private final SpellDao spells;
  private final UserSpellBookRepository userSpellBooks;

  public Optional<SpellBook> getSpellBook(int bookId) {
    var result = spellBooks.findById(bookId);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    SpellBook book = result.get();

    List<DatabaseSpellBookSpell> databaseSpells = spellBookSpells.findByBookId(bookId);
    for (DatabaseSpellBookSpell databaseSpell : databaseSpells) {
      spells.getSpell(databaseSpell.getSpellId()).ifPresent(spell -> book.getSpells().add(spell));
    }

    return Optional.of(book);
  }

  public SpellBook saveSpellBook(SpellBook book) {
    spellBooks.save(book);
    return book;
  }

  public void addBookToUser(int bookId, String userId) {
    userSpellBooks.addBookToUser(bookId, userId);
  }

  public void addSpellToBook(int bookId, int spellId) {
    spellBookSpells.addSpellToBook(bookId, spellId);
  }

  public void deleteSpellBook(int bookId) {
    spellBooks.deleteById(bookId);
    userSpellBooks.deleteBook(bookId);
    spellBookSpells.deleteBook(bookId);
  }
}
