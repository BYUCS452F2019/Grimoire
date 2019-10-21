import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserApiService {

  constructor(private httpClient: HttpClient) { }

  public getUser() {
      return this.httpClient.get(`https://grimoire.benwelker.com/api/user`);
  }
}