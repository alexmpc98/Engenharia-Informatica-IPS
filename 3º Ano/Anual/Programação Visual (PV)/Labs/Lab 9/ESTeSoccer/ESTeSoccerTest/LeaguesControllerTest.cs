using ESTeSoccer.Controllers;
using ESTeSoccer.Data;
using ESTeSoccerMVC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace ESTeSoccerTest
{
    public class LeaguesControllerTest : IClassFixture<ApplicationDbContextFixture>
    {
        private ESTeSoccerContext _context;

        public LeaguesControllerTest(ApplicationDbContextFixture contextFixture)
        {
            _context = contextFixture.DbContext;
        }
        [Fact]
        public async Task Index_ReturnsViewResult()
        {
            var lc = new LeaguesController(_context);
            var result = await lc.Index();
            var viewResult = Assert.IsType<ViewResult>(result);
            var model = Assert.IsAssignableFrom<IEnumerable<League>>(
                viewResult.ViewData.Model);
            Assert.NotNull(model);
            // Porque já contém a Liga BWIN e a Premier League no contexto
            Assert.Equal(3, model.Count());
        }
        // Liga Existente
        [Fact]
        public async Task Details_ReturnCorrectCountry()
        {
            var lc = new LeaguesController(_context);
            var result = await lc.Details(2);
            var viewResult = Assert.IsType<ViewResult>(result);
            var model = Assert.IsAssignableFrom<League>(
                viewResult.ViewData.Model);
            Assert.NotNull(viewResult);
            Assert.Equal("Inglaterra", model.Country);
            Assert.Equal("Premier League", model.Name);
        }
        // Liga Inexistente
        [Fact]
        public async Task Details_LeagueDoesNotExist()
        {
            var lc = new LeaguesController(_context);
            var result = await lc.Details(4);
            Assert.IsType<NotFoundResult>(result);
        }

        // Liga não existe quando é inserido null
        [Fact]
        public async Task Details_LeagueCantBeNull()
        {
            var lc = new LeaguesController(_context);
            var result = await lc.Details(null);
            Assert.IsType<NotFoundResult>(result);
        }
    }
}
