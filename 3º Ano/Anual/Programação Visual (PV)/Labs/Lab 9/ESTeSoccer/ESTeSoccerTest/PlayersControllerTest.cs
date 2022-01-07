using ESTeSoccer.Data;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xunit;
using ESTeSoccer.Controllers;
using Microsoft.AspNetCore.Mvc;
using ESTeSoccerMVC.Models;

namespace ESTeSoccerTest
{
    public class PlayersControllerTest : IClassFixture<ApplicationDbContextFixture>
    {
        private ESTeSoccerContext _context;
        public PlayersControllerTest(ApplicationDbContextFixture contextFixture)
        {
            _context = contextFixture.DbContext;
        }

        [Fact]
        public async Task Index_ReturnsViewResult()
        {
            var pc = new PlayersController(_context);
            var result = await pc.Index("",0);
            var viewResult = Assert.IsType<ViewResult>(result);
            var model = Assert.IsAssignableFrom<IEnumerable<Player>>(
                viewResult.ViewData.Model);
            Assert.NotNull(model);
            // Porque já contem os 5 jogadores criados no contexto do laboratório anterior
            Assert.Equal(5, model.Count());
        }

        [Fact]
        public async Task PlayerExists_PlayerReallyExists()
        {
            var pc = new PlayersController(_context);
            var result = pc.PlayerExists(1);
            Assert.True(result);  
        }

        [Fact]
        public async Task PlayerExists_PlayerDoesntExist()
        {
            var pc = new PlayersController(_context);
            var result = pc.PlayerExists(20);
            Assert.False(result);
        }
    }
}
