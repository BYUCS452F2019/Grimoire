import {observable, computed, action } from 'mobx';
import { Injectable } from '@angular/core';
import { SpellApiService, SpellBookApiService } from '../services';
import * as _ from 'lodash';

@Injectable()
export class SpellStoreService {
    @observable allSpells = [];
    @observable currentId;
    @observable currentBook;
    @observable filterName = '';
    @observable filterLevel;
    @observable filterClass = '';
    @observable filterSchool = '';
    @observable filterDamageType = '';


    constructor(private spellApi: SpellApiService, private bookApi: SpellBookApiService){
        this.spellApi.getAllSpells().subscribe((spells: any) => { 
            this.allSpells = spells
        });
    }

    @computed get currentSpell() {
        return this.allSpells.find((spell) => {
            return spell.spellId === this.currentId
        });
    }

    @computed get filteredSpells() {
        if (!this.currentBook) return;
        return this.currentBook.spells.filter((spell) => {
            if (this.filterName) {
                if (!spell.spellName.toLowerCase().includes(this.filterName.toLowerCase())) {
                    return false;
                }
            }
            if (!_.isNil(this.filterLevel)) {
                if (spell.level !== this.filterLevel) {
                    return false;
                }
            }
            if (this.filterClass) {
                if (spell.classType.every((spellClass) =>  spellClass !== this.filterClass.toString())) {
                    return false;
                }
            }
            if (this.filterSchool) {
                if (spell.school !== this.filterSchool.toString()) {
                    return false;
                }
            }
            if (this.filterDamageType) {
                if (spell.damageType !== this.filterDamageType.toString()) {
                    return false;
                }
            }

            return true;
        })
    }

    @action
    getCurrentBook(bookId) {
        if (bookId === 'all') {
            this.currentBook = {
                name: 'All Spells',
                spells: this.allSpells,
            }
        } else {
            this.bookApi.getSpellBook(bookId).subscribe((book) => {
                this.currentBook = book
            })
        }
    }

}