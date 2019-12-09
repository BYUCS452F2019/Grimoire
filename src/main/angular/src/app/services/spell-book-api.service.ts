import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpellBookApiService {
  constructor(private httpClient: HttpClient) {}

  public addSpellBook(bookName) {
    return this.httpClient.post(
      `/api/spellbooks?name=${bookName}`,
      null
    );
  }

  public addSpellToBook(bookId, spellId) {
    return this.httpClient.post(
      `/api/spellbooks/${bookId}?spellId=${spellId}`,
      null
    );
  }

  public getSpellBook(id) {
    return this.httpClient.get(`/api/spellbooks/${id}`);
  }

  public deleteSpellBook(id: number) {
    return this.httpClient.delete(`/api/spellbooks/${id}`);
  }

  public removeSpellFromBook(bookId: number, spellId: number) {
    return this.httpClient.delete(
      `/api/spellbooks/${bookId}/${spellId}`
    );
  }
}
