import { Component, OnInit } from '@angular/core';
import { UserApiService, SpellBookApiService } from 'src/app/services';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    user = {books: []};

    constructor(private userApi: UserApiService, private bookApi: SpellBookApiService) {
        this.userApi.getUser().subscribe((user: any) => {
            this.user = user;
        });
    }

    ngOnInit() {
    }

    createSpell() {
        // TODO route to create spell page when it exists
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
        this.bookApi.addSpellBook(book.name).subscribe((returnedBook: any) => {
            book = returnedBook.bookId
        });
    }

}
