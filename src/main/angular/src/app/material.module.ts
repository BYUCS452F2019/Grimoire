import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';

const modules = [MatToolbarModule];

@NgModule({
  imports: modules,
  exports: modules
})
export class MaterialModule { }
