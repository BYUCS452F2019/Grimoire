import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { School, ClassType } from '../../shared/interfaces/spell';

@Component({
  selector: 'app-spell-form',
  templateUrl: './spell-form.component.html',
  styleUrls: ['./spell-form.component.scss']
})
export class SpellFormComponent implements OnInit {
  newSpellForm: FormGroup;
  schoolOptions;
  classOptions;
  constructor(private fb: FormBuilder) {
    this.newSpellForm = this.fb.group({});

    this.schoolOptions = Object.keys(School).map(a => ({
      label: a,
      value: School[a]
    }));
    this.classOptions = Object.keys(ClassType).filter(
      k => typeof ClassType[k as any] === 'number'
    );
  }

  ngOnInit() { }

  submit(form) {
    console.log(form);
  }
}
