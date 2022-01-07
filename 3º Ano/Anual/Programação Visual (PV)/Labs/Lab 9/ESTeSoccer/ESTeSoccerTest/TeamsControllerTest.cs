using ESTeSoccer.Controllers;
using ESTeSoccer.Data;
using ESTeSoccerMVC.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;

namespace ESTeSoccerTest
{
    public class TeamsControllerTest : IClassFixture<ApplicationDbContextFixture>
    {
        private ESTeSoccerContext _context;
        public TeamsControllerTest(ApplicationDbContextFixture contextFixture)
        {
            _context = contextFixture.DbContext;
        }

        [Fact]
        public async Task Index_ReturnsViewResult()
        {
            var tc = new TeamsController(_context);
            var result = await tc.Index();
            var viewResult = Assert.IsType<ViewResult>(result);
            var model = Assert.IsAssignableFrom<IEnumerable<Team>>(
                viewResult.ViewData.Model);
            Assert.NotNull(model);
            // Porque já contém as 7 equipas do enunciado do laboratório anterior
            Assert.Equal(9, model.Count());
        }

        [Fact]
        public async Task Create_TeamReturnViewResult()
        {
            var tc = new TeamsController(_context);
            var result = tc.Create();
            Assert.NotNull(result);
            Assert.IsType<ViewResult>(result);
        }

        [Fact]
        public async Task SecondCreateTeam_ReturnViewResult()
        {
            var tc = new TeamsController(_context);
            Team tm =
            new Team
            {
                TeamId = 10,
                LeagueId = 2,
                Name = "Brighton",
                Initials = "BRI",
                NumberOfTitles = 2,
                MainColor = "Azul"
            };
            var result = tc.Create(tm);
            Assert.NotNull(result);

            //var viewResult = Assert.IsType<Task<IActionResult>>(result);
            var result2 = await tc.Index();
            var viewResult2 = Assert.IsType<ViewResult>(result2);
            var model = Assert.IsAssignableFrom<IEnumerable<Team>>(
                viewResult2.ViewData.Model);
            Assert.NotNull(model);
            // Porque já contém as 7 equipas do enunciado do laboratório anterior + 2 do contexto de teste
            Assert.Equal(10, model.Count());
        }
    }
}
