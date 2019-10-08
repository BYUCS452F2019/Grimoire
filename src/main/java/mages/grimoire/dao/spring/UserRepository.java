package mages.grimoire.dao.spring;

import mages.grimoire.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/** UserRepository */
public interface UserRepository extends CrudRepository<User, String> {

  @Modifying
  @Query("INSERT INTO user (user_id, name) VALUE (:userId, :name)")
  void createUser(@Param("userId") String userId, @Param("name") String name);
}
