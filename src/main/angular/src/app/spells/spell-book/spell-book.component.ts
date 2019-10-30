import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SpellStoreService } from '../spell-store.service';
import { School, Target, DamageType, ClassType } from '../../shared/interfaces/spell';

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    selector: 'app-spell-book',
    templateUrl: './spell-book.component.html',
    styleUrls: ['./spell-book.component.scss'],
})
export class SpellBookComponent implements OnInit {
  
    constructor(private router: Router, 
        private route: ActivatedRoute, 
        public spellStore: SpellStoreService) {
    }

    schoolOptions = Object.keys(School).sort();
    classOptions = Object.keys(ClassType).sort();
    targetOptions = Object.keys(Target).sort();
    damageTypeOptions = Object.keys(DamageType).sort();

    ngOnInit() {
        this.route.paramMap.subscribe((params) => {
            this.spellStore.getCurrentBook(params.get('bookId'));
        })
    }

    routeToSpell(spellId) {
        this.router.navigate(['spells/view/', spellId])
    }

}
