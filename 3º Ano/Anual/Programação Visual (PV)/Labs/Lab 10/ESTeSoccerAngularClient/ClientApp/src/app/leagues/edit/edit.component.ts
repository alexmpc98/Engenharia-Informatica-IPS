import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { League } from '../leagues';
import { LeaguesService } from '../leagues.service';


@Component({
  selector: 'app-edit',
  templateUrl: './edit.component.html',
  styleUrls: ['./edit.component.css']
})
export class EditComponent implements OnInit {
  id!: number;
  league!: League;
  editForm;

  constructor(public leaguesService: LeaguesService, private route: ActivatedRoute, private router: Router,
    private formBuilder: FormBuilder)
  {
    this.editForm = this.formBuilder.group({
      leagueId: [''],
      name: ['', Validators.required],
      country: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['leagueId'];

    this.leaguesService.getLeague(this.id).subscribe((data: League) => {
      this.league = data;
      this.editForm.patchValue(data);
    })
  }

  onSubmit(formData : any) {
    this.leaguesService.updateLeague(this.id, formData.value).subscribe(res => {
      this.router.navigateByUrl('/ligas');
    });
  }
}
