import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpellsRoutingModule } from './spells-routing.module';
import { SpellFormComponent } from './spell-form/spell-form.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';
import { SpellViewComponent } from './spell-view/spell-view.component';
import { SpellStoreService } from './spell-store.service';
import { MobxAngularModule } from 'mobx-angular';
import { SpellBookComponent } from './spell-book/spell-book.component';
import { AddSpellToBookComponent } from './add-spell-to-book/add-spell-to-book.component'



@NgModule({
  declarations: [SpellFormComponent, SpellViewComponent, SpellBookComponent, AddSpellToBookComponent],
  imports: [
    CommonModule,
    SpellsRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    MobxAngularModule,
  ],
  providers: [SpellStoreService]
})
export class SpellsModule { }
