package mages.grimoire.dao.spring;

import mages.grimoire.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/** UserRepository */
public interface UserRepository extends MongoRepository<User, String> {}
