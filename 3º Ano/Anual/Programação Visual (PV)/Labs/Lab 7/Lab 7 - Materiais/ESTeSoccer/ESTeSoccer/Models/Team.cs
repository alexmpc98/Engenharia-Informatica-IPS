using System.ComponentModel.DataAnnotations;

namespace ESTeSoccerMVC.Models
{
    public class Team
    {
        [Display(Name = "Id")]
        public int TeamId { get; set; }

        [Display(Name = "Liga")]
        public int LeagueId { get; set; }

        [Required]
        [Display(Name = "Nome")]
        public string Name { get; set; }

        [Required]
        [StringLength(3)]
        [Display(Name = "Sigla")]
        public string Initials { get; set; }

        [Display(Name = "Data de fundação")]
        public DateTime? FoundingDate { get; set; }

        [Range(0, int.MaxValue, ErrorMessage = "Only positive number allowed.")]
        [Display(Name = "Número de titulos")]
        public int NumberOfTitles { get; set; }

        [Display(Name = "Cor principal")]
        public string? MainColor { get; set; }

        public virtual List<Player>? Players { get; set; }

        [Display(Name = "Liga")]
        public League? League { get; set; }
    }
}
