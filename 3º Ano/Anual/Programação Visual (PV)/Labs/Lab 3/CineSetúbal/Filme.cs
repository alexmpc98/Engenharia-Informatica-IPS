using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CineSetúbal
{
    public class Filme
    {
        public string Titulo { get; set; }
        public string Diretor { get; set; }
        public List<String> Atores { get;set; }
        public int Duracao { get; set; }
        public int Ano { get; set; }
        public bool Estreia { get; set; }
    }
}
