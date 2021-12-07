using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace MiniESTS.Models
{
    public class Produto
    {
        [Key]
        public int ProdutoId { get; set; }
        public int LojaId { get; set; }
        public string Nome { get; set; }

        
       

        [EnumDataType(typeof(Tipo))]
        public Tipo enumTipo { get; set; }

        [Column(TypeName = "decimal(10, 2)")]
        [Range(0, 999.99),DataType(DataType.Currency)]
        public double Preco { get; set; }
        public int Unidades
        {
            get; set;
        }
        public Loja Loja { get; set; }
    }
    public enum Tipo
    {
        Congelados,
        Bebidas,
        Carnes,
        Frescos,
        Enlatados
    }
}
