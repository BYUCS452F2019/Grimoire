package mages.grimoire.dao.spring;

import mages.grimoire.model.SpellBook;
import org.springframework.data.repository.CrudRepository;

/** SpellBookRepository */
public interface SpellBookRepository extends CrudRepository<SpellBook, Integer> {}
