using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using ESTeSoccer.Data;
using ESTeSoccerMVC.Models;
using ESTeSoccer.Utils;

namespace ESTeSoccer.Controllers
{
    public class PlayersController : Controller
    {
        private readonly ESTeSoccerContext _context;

        private readonly int _pageSize = 5;

        public PlayersController(ESTeSoccerContext context)
        {
            _context = context;
        }

        // GET: Players
        public async Task<IActionResult> Index(string sortOrder, int? pageNumber)
        {
            ViewData["CurrentSort"] = sortOrder;
            ViewData["TeamSortParm"] = String.IsNullOrEmpty(sortOrder) ? "name_desc" : "";
            ViewData["DateSortParm"] = sortOrder == "Date" ? "date_desc" : "Date";

            var eSTeSoccerContext = _context.Player.Include(p => p.Team).Where(p => 0 == 0);
            switch (sortOrder)
            {
                case "name_desc":
                    eSTeSoccerContext = eSTeSoccerContext.OrderByDescending(s => s.TeamId);
                    break;
                case "Date":
                    eSTeSoccerContext = eSTeSoccerContext.OrderBy(s => s.DateOfBirth);
                    break;
                case "date_desc":
                    eSTeSoccerContext = eSTeSoccerContext.OrderByDescending(s => s.DateOfBirth);
                    break;
                default:
                    eSTeSoccerContext = eSTeSoccerContext.OrderBy(s => s.TeamId);
                    break;
            }

            return View(await PaginatedList<Player>.CreateAsync(eSTeSoccerContext.AsNoTracking(), pageNumber ?? 1, _pageSize));

            //return View(await eSTeSoccerContext.AsNoTracking().ToListAsync());
        }

        // GET: PlayersByTeam
        public async Task<IActionResult> PlayersByTeam(int teamId, int? pageNumber)
        {
            var playersAndTeams = _context.Player.Include(p => p.Team).Where(p => p.Team.TeamId == teamId);
            return View("Index", await PaginatedList<Player>.CreateAsync(playersAndTeams.AsNoTracking(), pageNumber ?? 1, _pageSize));
        }

        // GET: Players/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var player = await _context.Player
                .Include(p => p.Team)
                .FirstOrDefaultAsync(m => m.PlayerId == id);
            if (player == null)
            {
                return NotFound();
            }

            return View(player);
        }

        // GET: Players/Create
        public IActionResult Create()
        {
            ViewData["TeamId"] = new SelectList(_context.Team, "TeamId", "TeamId");
            return View();
        }

        // POST: Players/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("PlayerId,TeamId,Name,DateOfBirth,MarketValue")] Player player)
        {
            if (ModelState.IsValid)
            {
                _context.Add(player);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            ViewData["TeamId"] = new SelectList(_context.Team, "TeamId", "TeamId", player.TeamId);
            return View(player);
        }

        // GET: Players/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var player = await _context.Player.FindAsync(id);
            if (player == null)
            {
                return NotFound();
            }
            ViewData["TeamId"] = new SelectList(_context.Team, "TeamId", "TeamId", player.TeamId);
            return View(player);
        }

        // POST: Players/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("PlayerId,TeamId,Name,DateOfBirth,MarketValue")] Player player)
        {
            if (id != player.PlayerId)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(player);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!PlayerExists(player.PlayerId))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            ViewData["TeamId"] = new SelectList(_context.Team, "TeamId", "TeamId", player.TeamId);
            return View(player);
        }

        // GET: Players/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var player = await _context.Player
                .Include(p => p.Team)
                .FirstOrDefaultAsync(m => m.PlayerId == id);
            if (player == null)
            {
                return NotFound();
            }

            return View(player);
        }

        // POST: Players/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var player = await _context.Player.FindAsync(id);
            _context.Player.Remove(player);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool PlayerExists(int id)
        {
            return _context.Player.Any(e => e.PlayerId == id);
        }
    }
}
