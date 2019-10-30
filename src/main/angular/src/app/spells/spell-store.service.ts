import {observable, computed, action } from 'mobx';
import { Injectable } from '@angular/core';
import { SpellApiService, SpellBookApiService } from '../services';

@Injectable()
export class SpellStoreService {
    @observable allSpells = [];
    @observable currentId;
    @observable currentBook;
    @observable filterName;
    @observable filterLevel;
    @observable filterClass;
    @observable filterSchool;
    @observable filterDamageType;


    constructor(private spellApi: SpellApiService, private bookApi: SpellBookApiService){
        this.spellApi.getAllSpells().subscribe((spells: any) => this.allSpells = spells);
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
                if (!spell.spellName.includes(this.filterName)) {
                    return false;
                }
            }
            if (this.filterLevel) {
                if (spell.level !== this.filterLevel) {
                    return false;
                }
            }
            if (this.filterClass) {
                if (spell.classType.evry((spellClass) => spellClass !== this.filterClass)) {
                    return false;
                }
            }
            if (this.filterSchool) {
                if (spell.school !== this.filterSchool) {
                    return false;
                }
            }
            if (this.filterDamageType) {
                if (spell.damageType !== this.filterDamageType) {
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
                debugger;
                this.currentBook = book
            })
        }
    }
}