import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SpellStoreService } from '../spell-store.service';

@Component({
    changeDetection: ChangeDetectionStrategy.OnPush,
    selector: 'app-spell-view',
    templateUrl: './spell-view.component.html',
    styleUrls: ['./spell-view.component.scss'],
})
export class SpellViewComponent implements OnInit {
  
    constructor(private router: Router, private route: ActivatedRoute, public spellStore: SpellStoreService) {
    }

    ngOnInit() {
        this.route.paramMap.subscribe((params) => {
            this.spellStore.currentId = +params.get('spellId');
        })
    }

    addSpell(spell) {
        this.router.navigate(['spells/add/', spell.spellId]);
    }

}
