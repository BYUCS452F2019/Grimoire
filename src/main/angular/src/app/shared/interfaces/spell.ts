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
  Abjuration = 'Abjuration',
  Conjuration = 'Conjuration',
  Divination = 'Divination',
  Enchantment = 'Enchantment',
  Evocation = 'Evocation',
  Illusion = 'Illusion',
  Necromancy = 'Necromancy',
  Transmutation = 'Transmutation'
}

export enum Target {
  Self = 'Self',
  Creature = 'Creature',
  Cone = 'Cone',
  Cube = 'Cube',
  Cylinder = 'Cylinder',
  Line = 'Line',
  Sphere = 'Sphere'
}

export enum DamageType {
  Acid = 'Acid',
  Bludgeoning = 'Bludgeoning',
  Cold = 'Cold',
  Fire = 'Fire',
  Force = 'Force',
  Lightning = 'Lightning',
  Necrotic = 'Necrotic',
  Piercing = 'Piercing',
  Poison = 'Poison',
  Psychic = 'Psychic',
  Radiant = 'Radiant',
  Slashing = 'Slashing',
  Thunder = 'Thunder'
}

export enum ClassType {
  Bard = 'Bard',
  Cleric = 'Cleric',
  Druid = 'Druid',
  Paladin = 'Paladin',
  Ranger = 'Ranger',
  Sorcerer = 'Sorcerer',
  Warlock = 'Warlock',
  Wizard = 'Wizard'
}
