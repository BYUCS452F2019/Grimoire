import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpellBookApiService {

  constructor(private httpClient: HttpClient) { }

  public addSpellBook(bookName) {
      return this.httpClient.post(`https://grimoire.benwelker.com/api/spellbooks?name=${bookName}`, null);
  }

  public addSpellToBook(bookId, spellId) {
      return this.httpClient.post(`https://grimoire.benwelker.com/api/spellbooks/${bookId}?spellId=${spellId}`, null);
  }

  public getSpellBook(bookId) {
      return this.httpClient.get(`https://grimoire.benwelker.com/api/spellbooks/${bookId}`);
  }

}