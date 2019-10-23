import {observable, computed, action } from 'mobx';
import { Injectable } from '@angular/core';
import { SpellApiService } from '../services';

@Injectable()
export class SpellStoreService {
    @observable allSpells = [];
    @observable currentId;

    constructor(private spellApi: SpellApiService){
        // TODO make this use the actual api instead of the dummy data
        // this.spellApi.getAllSpells().subscribe((spells: any) => this.allSpells = spells);
        this.allSpells = [
        {
            book: 1,
            castingTime: '1 action',
            classType: 'Bard',
            concentration: true,
            damage: '1d6',
            damageType: 'Acid',
            description: 'I do not know I just made this up',
            duration: 0,
            higherLevels: '',
            level: 1,
            material: 'a drop of spit',
            range: 30,
            ritual: false,
            saving_throw: '',
            school: 'Abjuration',
            somatic: true,
            spellId: 0,
            spellName: 'Stinging Spit',
            target: '1 creature',
            verbal: false
        },
    ];
    }

    @computed get currentSpell() {
        return this.allSpells.find((spell) => {
            return spell.spellId === this.currentId
        });
    }
}