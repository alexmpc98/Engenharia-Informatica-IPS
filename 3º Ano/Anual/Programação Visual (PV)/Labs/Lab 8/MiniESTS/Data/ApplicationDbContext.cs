using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using MiniESTS.Models;

namespace MiniESTS.Data
{
    public class ApplicationDbContext : IdentityDbContext<Cliente>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        //Nivel 2
        protected override void OnModelCreating(ModelBuilder builder)
        {
            base.OnModelCreating(builder);

            builder.Entity<Produto>().HasData(
               new Produto
               {
                   ProdutoId = 1,
                   LojaId = 1,
                   Nome = "Coca-cola",
                   Preco = 1.80,
                   Tipo = Tipo.Bebidas,
                   Unidades = 10,
                   ComDesconto = true
               },
               new Produto
               {
                   ProdutoId = 2,
                   LojaId = 1,
                   Nome = "Alface",
                   Preco = 0.80,
                   Tipo = Tipo.Frescos,
                   Unidades = 15,
                   ComDesconto = false
               },
               new Produto
               {
                   ProdutoId = 3,
                   LojaId = 2,
                   Nome = "Almondegas",
                   Preco = 3.50,
                   Tipo = Tipo.Carnes,
                   Unidades = 20,
                   ComDesconto = true
               },
               new Produto
               {
                   ProdutoId = 4,
                   LojaId = 2,
                   Nome = "Àgua 1,5L",
                   Preco = 0.50,
                   Tipo = Tipo.Bebidas,
                   Unidades = 50,
                   ComDesconto = false
               },
               new Produto
               {
                   ProdutoId = 5,
                   LojaId = 2,
                   Nome = "Atum",
                   Preco = 1.50,
                   Tipo = Tipo.Enlatados,
                   Unidades = 50,
                   ComDesconto = true
               });

            builder.Entity<Loja>().HasData(
               new Loja
               {
                   LojaId = 1,
                   Localidade = "Setúbal"
               },
               new Loja
               {
                   LojaId = 2,
                   Localidade = "Lisboa"
               });
        }

        public DbSet<MiniESTS.Models.Loja> Loja { get; set; }
        public DbSet<MiniESTS.Models.Produto> Produto { get; set; }
        public DbSet<MiniESTS.Models.Carrinho> Carrinho { get; set; }
    }
}