import { Component, OnInit } from '@angular/core';
import { UserApiService } from 'src/app/services';

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
    user;

    constructor(private userApi: UserApiService) {
        this.userApi.getUser().subscribe((user) => {
            console.log(user); // TODO remove this console.log.
            this.user = user;
        });
    }

    ngOnInit() {
    }

    createSpell() {
        // TODO route to create spell page when it exists
    }

    search(bookId) {
    }

    newBook(){}

}
