import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { School, ClassType } from 'src/app/shared/interfaces/spell';
import { Router } from '@angular/router';

@Component({
  selector: 'app-spell-form',
  templateUrl: './spell-form.component.html',
  styleUrls: ['./spell-form.component.scss']
})
export class SpellFormComponent implements OnInit {
  newSpellForm: FormGroup;
  schoolOptions;
  classOptions;
  constructor(private fb: FormBuilder, private router: Router) {
    //   TODO add validation to the form
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
      if (form.valid) {
        // send new spell to the endpoint
      }
  }

  cancel() {
    this.router.navigate([''])
  }
}
