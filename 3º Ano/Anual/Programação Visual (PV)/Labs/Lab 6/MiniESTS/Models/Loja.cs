using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace MiniESTS.Models
{
    public class Loja
    {
        [Key]
        public int LojaId { get; set; }

        public List<Produto> Stock { get; set; }
        public string Localidade { get; set; }
    }
}
