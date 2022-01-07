using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MiniESTS.Data;
using MiniESTS.Models;

namespace MiniESTS.Controllers
{
    [Authorize(Roles = "cliente")]
    public class CarrinhoController : Controller
    {
        private readonly ApplicationDbContext _context;
        private readonly UserManager<Cliente> _userManager;

        public CarrinhoController(ApplicationDbContext context, UserManager<Cliente> userManager)
        {
            _context = context;
            _userManager = userManager;
        }

        // GET: Carrinho
        public async Task<IActionResult> Index()
        {
            var user = await _userManager.FindByNameAsync(User.Identity.Name);
            var applicationDbContext = _context.Carrinho.Include(c => c.Produto).Where(c => c.ClienteId == user.Id);
            ViewData["Message"] = TempData["Message"];
            TempData["Message"] = "";
            return View(await applicationDbContext.ToListAsync());
        }

        [HttpPost]
        public async Task<IActionResult> AddToCart(int id, int quantity)
        {
            var user = await _userManager.FindByNameAsync(User.Identity.Name);
            var produto = await _context.Produto.Where(p => p.ProdutoId == id).FirstOrDefaultAsync();
            _context.Add(new Carrinho 
            {
                ClienteId = user.Id,
                ProdutoId = id,
                Quantity = quantity,
                Produto = produto,
                Preco = quantity * produto.Preco
            });
            await _context.SaveChangesAsync();
            return RedirectToAction("Index");
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Delete(int id)
        {
            var item = await _context.Carrinho.FindAsync(id);
            _context.Carrinho.Remove(item);
            await _context.SaveChangesAsync();
            TempData["Message"] = "Item removido com sucesso";
            return RedirectToAction(nameof(Index));
        }

        //Desafio
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> FinishPurchase(string clientId)
        {
            var cart = await _context.Carrinho.Include(c => c.Produto).Where(c => c.ClienteId.Equals(clientId)).ToListAsync();
            foreach(var item in cart)
            {
                var produto = item.Produto;
                produto.Unidades -= item.Quantity;
                _context.Produto.Update(item.Produto);
            }
            _context.Carrinho.RemoveRange(cart);
            await _context.SaveChangesAsync();
            TempData["Message"] = "Obrigado por fazer as suas compras na MiniESTS!";
            return RedirectToAction(nameof(Index));
        }

    }
}
