using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ESTeSoccerMVC.Models;

namespace ESTeSoccer.Data
{
    public class ESTeSoccerContext : DbContext
    {
        public ESTeSoccerContext(DbContextOptions<ESTeSoccerContext> options)
            : base(options)
        {
        }

        public DbSet<ESTeSoccerMVC.Models.League> League { get; set; }

        public DbSet<ESTeSoccerMVC.Models.Team> Team { get; set; }

        public DbSet<ESTeSoccerMVC.Models.Player> Player { get; set; }

        // Specify DbSet properties etc
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // add your own configuration here
            modelBuilder.Entity<League>().HasData(
                    new League
                    {
                        LeagueId = 1,
                        Name = "Liga Bwin",
                        Country = "Portugal"
                    },
                    new League
                    {
                        LeagueId = 2,
                        Name = "Premier League",
                        Country = "Inglaterra"
                    }
                );

            modelBuilder.Entity<Team>().HasData(
                    new Team
                    {
                        TeamId = 1,
                        LeagueId = 1,
                        Name = "Sporting",
                        Initials = "SCP",
                        NumberOfTitles = 19,
                        MainColor = "Verde"
                    },
                    new Team
                    {
                        TeamId = 2,
                        LeagueId = 1,
                        Name = "Porto",
                        Initials = "FCP",
                        NumberOfTitles = 29,
                        MainColor = "Azul"
                    },
                    new Team
                    {
                        TeamId = 3,
                        LeagueId = 1,
                        Name = "Benfica",
                        Initials = "SLB",
                        NumberOfTitles = 35,
                        MainColor = "Vermelho"
                    },

                    new Team
                    {
                        TeamId = 4,
                        LeagueId = 2,
                        Name = "Chelsea",
                        Initials = "CHE",
                        NumberOfTitles = 3,
                        MainColor = "Azul"
                    },
                    new Team
                    {
                        TeamId = 5,
                        LeagueId = 2,
                        Name = "Manchester City",
                        Initials = "MCI",
                        NumberOfTitles = 6,
                        MainColor = "Azul"
                    },
                    new Team
                    {
                        TeamId = 6,
                        LeagueId = 2,
                        Name = "Manchester United",
                        Initials = "MUN",
                        NumberOfTitles = 35,
                        MainColor = "Vermelho"
                    },
                    new Team
                    {
                        TeamId = 7,
                        LeagueId = 2,
                        Name = "Liverpool",
                        Initials = "LIV",
                        NumberOfTitles = 35,
                        MainColor = "Vermelho"
                    }
                );

            modelBuilder.Entity<Player>().HasData(
                        new Player
                        {
                            PlayerId = 1,
                            TeamId = 1,
                            Name = "Pote",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 25000000
                        },
                        new Player
                        {
                            PlayerId = 2,
                            TeamId = 1,
                            Name = "Paulinho",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 15000000
                        },
                        new Player
                        {
                            PlayerId = 3,
                            TeamId = 2,
                            Name = "Francisco Conceição",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 10000000
                        },
                        new Player
                        {
                            PlayerId = 4,
                            TeamId = 2,
                            Name = "Luis Diaz",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 25000000
                        },
                        new Player
                        {
                            PlayerId = 5,
                            TeamId = 3,
                            Name = "Rafa",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 25000000
                        },
                        new Player
                        {
                            PlayerId = 6,
                            TeamId = 3,
                            Name = "Grimaldo",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 20000000
                        },
                        new Player
                        {
                            PlayerId = 7,
                            TeamId = 4,
                            Name = "Lukaku",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 45000000
                        },
                        new Player
                        {
                            PlayerId = 8,
                            TeamId = 4,
                            Name = "Harvertz",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 25000000
                        },
                        new Player
                        {
                            PlayerId = 9,
                            TeamId = 5,
                            Name = "De Bruyne",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 100000000
                        },
                        new Player
                        {
                            PlayerId = 10,
                            TeamId = 5,
                            Name = "Bernardo Silva",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 45000000
                        },
                        new Player
                        {
                            PlayerId = 11,
                            TeamId = 6,
                            Name = "Ronaldo",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 75000000
                        },
                        new Player
                        {
                            PlayerId = 12,
                            TeamId = 6,
                            Name = "Bruno Fernandes",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 80000000
                        },
                        new Player
                        {
                            PlayerId = 13,
                            TeamId = 7,
                            Name = "Salah",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 200000000
                        },
                        new Player
                        {
                            PlayerId = 14,
                            TeamId = 7,
                            Name = "Diogo Jota",
                            DateOfBirth = new DateTime(1990, 03, 22),
                            MarketValue = 50000000
                        }
                );
        }
    }
}
