using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Text;
using MiniESTS.Models;
using Microsoft.AspNetCore.Identity;

namespace MiniESTS.Data
{
    public class ApplicationDbContext : IdentityDbContext<Cliente>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            /*modelBuilder.Ignore<IdentityUserLogin<string>>();
            modelBuilder.Ignore<IdentityUserRole<string>>();
            modelBuilder.Ignore<IdentityUserClaim<string>>();
            modelBuilder.Ignore<IdentityUserToken<string>>();
            modelBuilder.Ignore<IdentityUser<string>>();
          */
            base.OnModelCreating(modelBuilder);
            // 2 lojas
            Loja loja1 = new Loja { LojaId = 1, Localidade = "Lisboa"};
            Loja loja2 = new Loja { LojaId = 2, Localidade = "Porto" };

            //5 produtos
            Produto produto1 = new Produto { ProdutoId = 1, LojaId = 1 , Nome = "Picanha", enumTipo = Tipo.Carnes
            , Preco = 10.99 , Unidades = 20};
            Produto produto2 = new Produto
            {
                ProdutoId = 2,
                LojaId = 1,
                Nome = "Atum",
                enumTipo = Tipo.Enlatados
            ,
                Preco = 8.99,
                Unidades = 10
            };
            Produto produto3 = new Produto
            {
                ProdutoId = 3,
                LojaId = 1,
                Nome = "Coca-Cola",
                enumTipo = Tipo.Bebidas
            ,
                Preco = 1.99,
                Unidades = 1
            };
            Produto produto4 = new Produto
            {
                ProdutoId = 4,
                LojaId = 2,
                Nome = "Camarão",
                enumTipo = Tipo.Congelados
            ,
                Preco = 11.99,
                Unidades = 13
            };
            Produto produto5 = new Produto
            {
                ProdutoId = 5,
                LojaId = 2,
                Nome = "Cogumelos",
                enumTipo = Tipo.Enlatados,
                Preco = 5.49,
                Unidades = 10
            };

            modelBuilder.Entity<Loja>().HasData(
                  loja1, loja2
               );
            modelBuilder.Entity<Produto>().HasData(
                  produto1, produto2, produto3, produto4, produto5
               );

        }
        public DbSet<MiniESTS.Models.Produto> Produto { get; set; }
        public DbSet<MiniESTS.Models.Loja> Loja { get; set; }
    }
}
