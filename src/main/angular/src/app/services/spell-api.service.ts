import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SpellApiService {

  constructor(private httpClient: HttpClient) { }

  public getAllSpells() {
      return this.httpClient.get(`/api/spells`);
  }

  public addSpell(spell) {
      return this.httpClient.post(`/api/spells`, spell);
  }
}
