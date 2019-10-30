import {observable, computed, action } from 'mobx';
import { Injectable } from '@angular/core';
import { SpellApiService } from '../services';

@Injectable()
export class SpellStoreService {
    @observable allSpells = [];
    @observable currentId;

    constructor(private spellApi: SpellApiService){
        this.spellApi.getAllSpells().subscribe((spells: any) => this.allSpells = spells);
    }

    @computed get currentSpell() {
        return this.allSpells.find((spell) => {
            return spell.spellId === this.currentId
        });
    }
}