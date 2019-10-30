import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpellFormComponent } from './spell-form/spell-form.component';
import { SpellViewComponent } from './spell-view/spell-view.component';
import { SpellBookComponent } from './spell-book/spell-book.component';


const routes: Routes = [
  { path: 'new', component: SpellFormComponent },
  { path: 'view/:spellId', component: SpellViewComponent},
  { path: 'book/:bookId', component: SpellBookComponent},
  { path: '**', pathMatch: 'full', redirectTo: 'new' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SpellsRoutingModule { }
