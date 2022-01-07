import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule  } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';


import { League } from "../leagues";
import { LeaguesService } from '../leagues.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {
  createForm;
  leagues: League[] = [];

  constructor(public leagueService: LeaguesService, private route: ActivatedRoute, private router: Router, private formBuilder: FormBuilder) {
    this.createForm = this.formBuilder.group({
      name: ['', Validators.required],
      country: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.leagueService.getLeagues().subscribe((data: League[]) => {
      this.leagues = data;
    })
  }

  onSubmit(formData : any) {
    this.leagueService.createLeague(formData.value).subscribe(res => {
      this.router.navigateByUrl('/ligas');
    })
  }

}
