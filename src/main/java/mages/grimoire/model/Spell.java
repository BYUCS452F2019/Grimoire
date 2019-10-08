package mages.grimoire.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

/** Spell */
@Data
public class Spell {

  @Id private int spellId;
  private String spellName;
  private int level;
  private boolean ritual;
  private String castingTime;

  @JsonProperty("range")
  private int rangeValue;

  private boolean verbal;
  private boolean somatic;
  private String material;
  private School school;
  private int duration;
  private boolean concentration;
  private Target target;
  private String saving_throw;
  private String description;
  private String higherLevels;
  private String damage;
  private DamageType damageType;
  private ClassType classType;
  private String book;

  public static enum School {
    Abjuration,
    Conjuration,
    Divination,
    Enchantment,
    Evocation,
    Illusion,
    Necromancy,
    Transmutation
  }

  public static enum Target {
    Self,
    Creature,
    Cone,
    Cube,
    Cylinder,
    Line,
    Sphere
  }

  public static enum DamageType {
    Acid,
    Bludgeoning,
    Cold,
    Fire,
    Force,
    Lightning,
    Necrotic,
    Piercing,
    Poison,
    Psychic,
    Radiant,
    Slashing,
    Thunder
  }

  public static enum ClassType {
    Bard,
    Cleric,
    Druid,
    Paladin,
    Ranger,
    Sorcerer,
    Warlock,
    Wizard
  }
}
