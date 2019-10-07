import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'grimoire';
  stuff = this.http.get('/api/v2/api-docs');

  constructor(private http: HttpClient) {

  }
}
