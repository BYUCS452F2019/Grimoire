import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SpellsRoutingModule } from './spells-routing.module';
import { SpellFormComponent } from './spell-form/spell-form.component';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material.module';


@NgModule({
  declarations: [SpellFormComponent],
  imports: [
    CommonModule,
    SpellsRoutingModule,
    ReactiveFormsModule,
    MaterialModule
  ]
})
export class SpellsModule { }
