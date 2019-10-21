import { Component, OnInit } from '@angular/core';
import { UserApiService, SpellBookApiService } from 'src/app/services';
import { Router } from '@angular/router';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    user = {books: [], name: ''};

    constructor(private userApi: UserApiService, private bookApi: SpellBookApiService, private router: Router) {
        this.userApi.getUser().subscribe((user: any) => {
            this.user = user;
        });
    }

    ngOnInit() {
    }

    createSpell() {
        this.router.navigate(['spells/new']);
    }

    search(bookId) {
        if (!bookId) {
            return
        }
        // TODO route to book search
    }

    newBook(){
        this.user.books.push({name: ''});
    }

    saveBookName(book) {
        if (book.name) {
            this.bookApi.addSpellBook(book.name).subscribe((returnedBook: any) => {
                book = returnedBook.bookId
            });
        }
    }

}
