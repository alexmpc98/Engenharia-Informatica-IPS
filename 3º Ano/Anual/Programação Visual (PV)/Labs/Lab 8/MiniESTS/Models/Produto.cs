using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniESTS.Models
{
    // Nivel 1
    public class Produto
    {
        public int ProdutoId { get; set; }
        public int LojaId { get; set; }
        public string Nome { get; set; }
        
        [EnumDataType(typeof(Tipo))]
        public Tipo Tipo { get; set; }
        
        [Column(TypeName = "decimal(10, 2)")]
        [Range(0,999.99), DataType(DataType.Currency)]
        public double Preco { get;set; }
        public int Unidades { get; set; }
        public bool ComDesconto { get; set; }
        public Loja Loja { get; set; }
    }
}
