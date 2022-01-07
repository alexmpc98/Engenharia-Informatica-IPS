using System.ComponentModel.DataAnnotations;

namespace ESTeSoccerMVC.Models
{
    public class Player
    {
        [Display(Name = "Id")]
        public int PlayerId { get; set; }

        [Display(Name = "Equipa")]
        public int? TeamId { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string Name { get; set; }

        [Required]
        [DataType(DataType.Date)]
        [Display(Name = "Data de nascimento")]
        public DateTime DateOfBirth { get; set; }

        [Range(0, int.MaxValue, ErrorMessage = "Only positive number allowed.")]
        [Display(Name = "Valor de Mercado")]
        public decimal? MarketValue { get; set; }

        [Display(Name = "Equipa")]
        public Team? Team { get; set; }
    }
}
