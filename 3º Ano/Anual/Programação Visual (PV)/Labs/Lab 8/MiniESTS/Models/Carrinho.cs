using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniESTS.Models
{
    public class Carrinho
    {
        [Key]
        public int ProdutoClienteId { get; set; }
        
        [ForeignKey("Produto")]
        public int ProdutoId { get; set; }
        [ForeignKey("Cliente")]
        public string ClienteId { get; set; }
        public Produto Produto { get; set; }
        public int Quantity { get; set; }
        public double Preco{ get; set; }
    }
}
