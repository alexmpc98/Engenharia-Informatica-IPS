using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using ESTeSoccer.Data;
using ESTeSoccerMVC.Models;

namespace ESTeSoccer.Controllers
{
    public class LeaguesController : Controller
    {
        private readonly ESTeSoccerContext _context;

        public LeaguesController(ESTeSoccerContext context)
        {
            _context = context;
        }

        // GET: Leagues
        public async Task<IActionResult> Index()
        {
            return View(await _context.League.ToListAsync());
        }

        // GET: Leagues/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var league = await _context.League.Include(l => l.Teams)
                .FirstOrDefaultAsync(m => m.LeagueId == id);

            if (league == null)
            {
                return NotFound();
            }

            return View(league);
        }

    }
}
