import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpellsRoutingModule } from './spells-routing.module';
import { SpellFormComponent } from './spell-form/spell-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';
import { SpellViewComponent } from './spell-view/spell-view.component';
import { SpellStoreService } from './spell-store.service';
import { MobxAngularModule } from 'mobx-angular';


@NgModule({
  declarations: [SpellFormComponent, SpellViewComponent],
  imports: [
    CommonModule,
    SpellsRoutingModule,
    ReactiveFormsModule,
    MaterialModule,
    MobxAngularModule,
  ],
  providers: [SpellStoreService]
})
export class SpellsModule { }
