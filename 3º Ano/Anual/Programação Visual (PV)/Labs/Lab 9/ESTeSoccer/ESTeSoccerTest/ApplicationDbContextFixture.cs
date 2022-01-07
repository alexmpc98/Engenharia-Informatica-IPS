using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ESTeSoccer.Data;
using Microsoft.EntityFrameworkCore;
using Microsoft.Data.Sqlite;
using ESTeSoccerMVC.Models;

namespace ESTeSoccerTest
{
    public class ApplicationDbContextFixture : IDisposable
    {
        public ApplicationDbContextFixture()
        {
            var connection = new SqliteConnection("DataSource=:memory:");
            connection.Open();
            var options = new DbContextOptionsBuilder<ESTeSoccerContext>()
                .UseSqlite(connection)
                .Options;
            DbContext = new ESTeSoccerContext(options);
            DbContext.Database.EnsureCreated();
            DbContext.League.Add(new League()
            {
                Name = "Liga BBVA",
                Country = "Espanha"
            });
            DbContext.Team.Add(new Team()
            {
                TeamId = 8,
                LeagueId = 1,
                Name = "Tondela",
                Initials = "TON",
                NumberOfTitles = 0,
                MainColor = "Amarelo"
            });
            DbContext.Team.Add(new Team()
            {
                TeamId = 9,
                LeagueId = 2,
                Name = "Leicester",
                Initials = "LEI",
                NumberOfTitles = 1,
                MainColor = "Azul"
            });
            DbContext.SaveChanges();
        }
        public ESTeSoccerContext DbContext { get; private set; }

        public void Dispose()
        {
            DbContext.Dispose();
            GC.SuppressFinalize(this);
        }
    }
}
