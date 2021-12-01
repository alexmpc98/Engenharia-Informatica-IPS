using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ESTeSoccer.Models;

namespace ESTeSoccer.Data
{
    public class ESTeSoccerContext : DbContext
    {
        public ESTeSoccerContext (DbContextOptions<ESTeSoccerContext> options)
            : base(options)
        {
        }

        public DbSet<ESTeSoccer.Models.Player> Player { get; set; }

        public DbSet<ESTeSoccer.Models.League> League { get; set; }

        public DbSet<ESTeSoccer.Models.Team> Team { get; set; }

        protected override void OnModelCreating(ModelBuilder model)
        {
            League ligaBwin = new League { Name = "Liga Bwin" , Country = "Portugal" , LeagueId = 1};
            League premierLeague = new League { Name = "Premier League", Country = "Inglaterra", LeagueId = 2 };
            Team porto = new Team { TeamId = 1, Name = "Porto" , FoundingDate = new DateTime(1896,05,04),
            Initials = "FCP" , MainColor = "Azul" , LeagueId = 1, NumberOfTitles = 40,
            };
            Team benfica = new Team
            {
                TeamId = 2,
                Name = "Benfica",
                FoundingDate = new DateTime(1904, 05, 04),
                Initials = "SLB",
                MainColor = "Vermelho",
                LeagueId = 1,
                NumberOfTitles = 40,
            };
            Team sporting = new Team
            {
                TeamId = 3,
                Name = "Sporting",
                FoundingDate = new DateTime(1906, 05, 04),
                Initials = "SCP",
                MainColor = "Verde",
                LeagueId = 1,
                NumberOfTitles = 40,
            };
            Team liverpool = new Team
            {
                TeamId = 4,
                Name = "Liverpool",
                FoundingDate = new DateTime(1906, 05, 04),
                Initials = "LFP",
                MainColor = "Vermelho",
                LeagueId = 2,
                NumberOfTitles = 100,
            };
            Team manutd = new Team
            {
                TeamId = 5,
                Name = "Manchester United",
                FoundingDate = new DateTime(1906, 05, 04),
                Initials = "MAN",
                MainColor = "Vermelho",
                LeagueId = 2,
                NumberOfTitles = 100,
            };
            Team mancity = new Team
            {
                TeamId = 6,
                Name = "Manchester City",
                FoundingDate = new DateTime(1906, 05, 04),
                Initials = "MCT",
                MainColor = "azul",
                LeagueId = 2,
                NumberOfTitles = 100,
            };
            Team chelsea = new Team
            {
                TeamId = 7,
                Name = "Chelsea",
                FoundingDate = new DateTime(1906, 05, 04),
                Initials = "CHL",
                MainColor = "azul",
                LeagueId = 2,
                NumberOfTitles = 100,
            };
            Player alex = new Player { 
                Name = "Alex", 
                TeamId = 1, 
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 1
            };
            Player alex2 = new Player
            {
                Name = "Alex2",
                TeamId = 1,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 2
            };
            Player figo = new Player
            {
                Name = "Figo",
                TeamId = 2,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 3
            };
            Player figo2 = new Player
            {
                Name = "Figo2",
                TeamId = 2,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 4
            };
            Player ronaldo = new Player
            {
                Name = "Ronaldo",
                TeamId = 3,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 5
            };
            Player ronaldo2 = new Player
            {
                Name = "Ronaldo2",
                TeamId = 3,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 6
            };
            Player salah = new Player
            {
                Name = "Salah",
                TeamId = 4,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 7
            };
            Player salah2 = new Player
            {
                Name = "Salah2",
                TeamId = 4,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 8
            };
            Player crisronaldo = new Player
            {
                Name = "Cristiano Ronaldo",
                TeamId = 5,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 9
            };
            Player crisronaldo2 = new Player
            {
                Name = "Cristiano Ronaldo2",
                TeamId = 5,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 10
            };
            Player bernardosilva = new Player
            {
                Name = "Bernardo Silva",
                TeamId = 6,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 11
            };
            Player bernardosilva2 = new Player
            {
                Name = "Bernardo Silva2",
                TeamId = 6,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 12
            };
            Player jorginho = new Player
            {
                Name = "Jorginho",
                TeamId = 7,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 13
            };
            Player jorginho2 = new Player
            {
                Name = "Jorginho2",
                TeamId = 7,
                DateOfBirth = new DateTime(1998, 06, 27),
                MarketValue = 2,
                Playerid = 14
            };

            /*
            porto.Players.Add(alex);
            porto.Players.Add(alex2);
            benfica.Players.Add(figo);
            benfica.Players.Add(figo2);
            sporting.Players.Add(ronaldo);
            sporting.Players.Add(ronaldo2);
            manutd.Players.Add(crisronaldo);
            manutd.Players.Add(crisronaldo2);


            ligaBwin.Teams.Add(porto);
            ligaBwin.Teams.Add(benfica);
            ligaBwin.Teams.Add(sporting);
            premierLeague.Teams.Add(chelsea);
            premierLeague.Teams.Add(mancity);
            premierLeague.Teams.Add(manutd);
            premierLeague.Teams.Add(liverpool);
            */

           
            
            model.Entity<League>().HasData(
                  ligaBwin, premierLeague
               );
            model.Entity<Team>().HasData(
                  porto, benfica, sporting, manutd, mancity, chelsea, liverpool
               );

            model.Entity<Player>().HasData(
                  alex, alex2, figo, figo2, crisronaldo, crisronaldo2, ronaldo, ronaldo2, salah, salah2,
                  bernardosilva, bernardosilva2, jorginho, jorginho2
               );
        }
    }
}
