export interface Spell {

  spellId?: number;
  spellName: string;
  level: number;
  ritual: boolean;
  castingTime: string;

  rangeValue: number;

  verbal: boolean;
  somatic: boolean;
  material: string;
  school: School;
  duration: number;
  concentration: boolean;
  target: Target;
  saving_throw: string;
  description: string;
  higherLevels: string;
  damage: string;
  damageType: DamageType;
  classType: ClassType;
  book: string;
}

export enum School {
  Abjuration,
  Conjuration,
  Divination,
  Enchantment,
  Evocation,
  Illusion,
  Necromancy,
  Transmutation
}

export enum Target {
  Self,
  Creature,
  Cone,
  Cube,
  Cylinder,
  Line,
  Sphere
}

export enum DamageType {
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

export enum ClassType {
  Bard,
  Cleric,
  Druid,
  Paladin,
  Ranger,
  Sorcerer,
  Warlock,
  Wizard
}
