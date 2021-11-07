using CineSetúbal.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;

namespace CineSetúbal.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;
        private List<Filme> filmes;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
            filmes = new List<Filme> {
                 new Filme
                 {
                     Titulo = "Pulp Fiction",
                     Diretor = "Quentin Tarantino",
                     Atores = new List<string>{"John Travolta", "Uma Thurman", "Samuel L. Jackson" },
                     Duracao = 154,
                     Ano = 1994,
                     Estreia = true
                 },
                 new Filme
                 {
                     Titulo = "Blade Runner",
                     Diretor = "Ridley Scott",
                     Atores = new List<string>{"Harrison Ford", "David Webb Peoples", "Philip K. Dick" },
                     Duracao = 117,
                     Ano = 1982,
                     Estreia = false
                 },
                 new Filme
                 {
                     Titulo = "Friday the 13th",
                     Diretor = "Sean S. Cunningham Leone",
                     Atores = new List<string>{"Betsy Palmer", "Adrienne King", "Jeannine Taylor" },
                     Duracao = 95,
                     Ano = 1980,
                     Estreia = false
                 },
                 new Filme
                 {
                     Titulo = "The Good, The Bad & The Ugly",
                     Diretor = "Sergio Leone",
                     Atores = new List<string>{"Clint Eastwood", "Eli Wallach", "Lee Van Cleef" },
                     Duracao = 161,
                     Ano = 1966,
                     Estreia = true
                 }
            };
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        public IActionResult About()
        {
            return View();
        }

        public IActionResult FilmeDoDia()
        {
            Filme movie = filmes[0];
            int movieIndex = filmes.FindIndex(a => a.Equals(movie)) + 1;
            ViewData["IndexFilme"] = "Filme " +  movieIndex.ToString() + " de " + filmes.Count().ToString();   
            return View(movie);
        }

        public IActionResult Contact()
        {
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
