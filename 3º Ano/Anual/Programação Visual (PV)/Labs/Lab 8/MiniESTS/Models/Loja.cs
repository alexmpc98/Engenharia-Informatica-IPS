namespace MiniESTS.Models
{
    // Nivel 1
    public class Loja
    {
        public int LojaId { get; set; }
        public List<Produto> Stock { get; set; }
        public string Localidade { get; set; }

    }
}
