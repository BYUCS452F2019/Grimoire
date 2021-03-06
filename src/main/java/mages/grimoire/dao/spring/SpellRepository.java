package mages.grimoire.dao.spring;

import mages.grimoire.model.Spell;
import org.springframework.data.repository.CrudRepository;

/** SpellRepository */
public interface SpellRepository extends CrudRepository<Spell, Integer> {}
