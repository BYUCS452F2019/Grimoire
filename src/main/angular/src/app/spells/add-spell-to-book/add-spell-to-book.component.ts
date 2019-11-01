import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Location } from '@angular/common';
import { SpellStoreService } from '../spell-store.service';
import { observable, computed } from 'mobx-angular';
import { ActivatedRoute } from '@angular/router';
import { SpellBookApiService, UserApiService } from 'src/app/services';

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    selector: 'app-add-spell-to-book',
    templateUrl: './add-spell-to-book.component.html',
    styleUrls: ['./add-spell-to-book.component.scss'],
})
export class AddSpellToBookComponent implements OnInit {
    @observable books = [];
    @observable spellId;
  
    constructor(
        private route: ActivatedRoute, 
        private location: Location, 
        public spellStore: SpellStoreService,
        private bookApi: SpellBookApiService,
        private userApi: UserApiService,
        public spellService: SpellStoreService) {
        this.userApi.getUser().subscribe((user: any) => this.books = user.books)
    }

    @computed get openBooks() {
        return this.books.filter((book) => {
            return book.spells.every((spell) => spell.spellId.toString() !== this.spellId);
        });
    }

    @computed get booksSelected() {
        return this.openBooks.some((book) => book.addSpell);
    }

    @computed get currentSpell() {
        return this.spellService.allSpells.find((spell) => spell.spellId.toString() === this.spellId);
    }

    ngOnInit() {
        this.route.paramMap.subscribe((params) => {
            this.spellId = params.get('spellId');
        })
    }

    cancel() {
        this.location.back();
    }

    addSpell() {
        this.openBooks.forEach((book) => {
            if (book.addSpell) {
                this.bookApi.addSpellToBook(book.bookId, this.spellId).subscribe();
            }
        })
        this.location.back();
    }

}
