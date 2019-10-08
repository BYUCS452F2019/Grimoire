package mages.grimoire.dao;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import mages.grimoire.dao.spring.UserRepository;
import mages.grimoire.dao.spring.UserSpellBookRepository;
import mages.grimoire.model.User;
import mages.grimoire.model.UserSpellBook;
import org.springframework.stereotype.Component;

/** UserDao */
@Component
@AllArgsConstructor
public class UserDao {

  private final UserRepository users;
  private final UserSpellBookRepository userBooks;
  private final SpellBookDao books;

  public Optional<User> getUser(String userId) {
    var result = users.findById(userId);
    if (result.isEmpty()) {
      return Optional.empty();
    }
    User user = result.get();

    List<UserSpellBook> userSpellBooks = userBooks.getUsersBooks(userId);
    for (UserSpellBook userSpellBook : userSpellBooks) {
      books.getSpellBook(userSpellBook.getBookId()).ifPresent(book -> user.getBooks().add(book));
    }

    return Optional.of(user);
  }

  public User addUser(User user) {
    users.createUser(user.getUserId(), user.getName());
    return user;
  }
}
