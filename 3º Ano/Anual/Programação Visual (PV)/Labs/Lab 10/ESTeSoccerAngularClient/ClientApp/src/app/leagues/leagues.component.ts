import { Component } from '@angular/core';
import { League } from './leagues'
import { LeaguesService } from './leagues.service';

@Component({
  selector: 'app-leagues',
  templateUrl: './leagues.component.html',
  styleUrls: ['./leagues.component.css']
})

export class LeaguesComponent {
  public leagues: League[] = [];

  constructor(public leagueService : LeaguesService) {
  }

  ngOnInit(): void {
    this.leagueService.getLeagues().subscribe((data: League[]) => { this.leagues = data; });
  }

  deleteLeague(id:number) {
    this.leagueService.deleteLeague(id).subscribe(res => { this.leagues = this.leagues.filter(item => item.leagueId !== id); });
  }
}
