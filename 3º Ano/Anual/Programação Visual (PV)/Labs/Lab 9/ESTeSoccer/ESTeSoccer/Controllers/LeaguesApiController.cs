using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ESTeSoccer.Data;
using ESTeSoccerMVC.Models;

namespace ESTeSoccer.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class LeaguesApiController : ControllerBase
    {
        private readonly ESTeSoccerContext _context;

        public LeaguesApiController(ESTeSoccerContext context)
        {
            _context = context;
        }

        // GET: api/LeaguesApi
        [HttpGet]
        public async Task<ActionResult<IEnumerable<League>>> GetLeague()
        {
            return await _context.League.ToListAsync();
        }

        // GET: api/LeaguesApi/5
        [HttpGet("{id}")]
        public async Task<ActionResult<League>> GetLeague(int id)
        {
            var league = await _context.League.FindAsync(id);

            if (league == null)
            {
                return NotFound();
            }

            return league;
        }

        // PUT: api/LeaguesApi/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutLeague(int id, League league)
        {
            if (id != league.LeagueId)
            {
                return BadRequest();
            }

            _context.Entry(league).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LeagueExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/LeaguesApi
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<League>> PostLeague(League league)
        {
            _context.League.Add(league);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetLeague", new { id = league.LeagueId }, league);
        }

        // DELETE: api/LeaguesApi/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteLeague(int id)
        {
            var league = await _context.League.FindAsync(id);
            if (league == null)
            {
                return NotFound();
            }

            _context.League.Remove(league);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool LeagueExists(int id)
        {
            return _context.League.Any(e => e.LeagueId == id);
        }


        // GET: api/LeaguesApi
        [HttpGet("LeagueAndTeams/{id}")]
        public async Task<ActionResult<object>> GetLeagueAndTeams(int id)
        {
            var league = await _context.League.Include(l => l.Teams)
                .Select(league => new
                {
                    LeagueId = league.LeagueId,
                    Name = league.Name,
                    Teams = league.Teams.Select(t => new 
                    {
                        Name = t.Name,
                        Initials = t.Initials

                    })
                })
                .FirstOrDefaultAsync(m => m.LeagueId == id);

            if (league == null)
            {
                return NotFound();
            }        


            return league;
        }

    }
}
