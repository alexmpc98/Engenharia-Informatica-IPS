import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { League } from "../leagues";
import { LeaguesService } from '../leagues.service';


@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent implements OnInit {
  id!: number;
  league!: League;

  constructor(public leagueServices: LeaguesService, private route: ActivatedRoute, private router: Router)
  {
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['leagueId'];
    this.leagueServices.getLeague(this.id).subscribe((data: League) => {
      this.league = data;
    });
  }
}
