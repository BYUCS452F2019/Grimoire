import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { School, ClassType, Target, DamageType, Spell } from 'src/app/shared/interfaces/spell';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { SpellApiService } from 'src/app/services';

@Component({
  selector: 'app-spell-form',
  templateUrl: './spell-form.component.html',
  styleUrls: ['./spell-form.component.scss']
})
export class SpellFormComponent implements OnInit {
  newSpellForm: FormGroup;
  schoolOptions = Object.keys(School).sort();
  classOptions = Object.keys(ClassType).sort();
  targetOptions = Object.keys(Target).sort();
  damageTypeOptions = Object.keys(DamageType).sort();
  constructor(private fb: FormBuilder, private router: Router, private spellService: SpellApiService) {
    //   TODO add validation to the form
    this.newSpellForm = fb.group({
      spellName: ['', Validators.required],
      level: ['', Validators.required],
      ritual: [false, Validators.required],
      castingTime: ['', Validators.required],
      rangeValue: ['', Validators.required],
      verbal: [false, Validators.required],
      somatic: [false, Validators.required],
      material: [''],
      school: ['', Validators.required],
      duration: ['', Validators.required],
      concentration: [false, Validators.required],
      target: [''],
      saving_throw: [''],
      description: ['', Validators.required],
      higherLevels: [''],
      damage: [''],
      damageType: [''],
      classType: fb.array(this.classOptions.map(() => fb.control(false)), Validators.required),
      book: ['', Validators.required],
    });
  }

  ngOnInit() { }

  submit(form: { classType: any[] }) {
    form.classType = form.classType.map((classType, i) => classType && this.classOptions[i]).filter(a => !!a);
    console.log(JSON.stringify(form));
    this.spellService.addSpell(form).subscribe(this.cancel);
  }

  cancel() {
    this.router.navigate(['']);
  }
}
