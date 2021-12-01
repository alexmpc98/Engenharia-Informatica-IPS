using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ESTeSoccer.Models
{
    public class Team
    {
        [Key] [Display(Name = "ID de Equipa")] public int TeamId { get; set; }
        //[ForeignKey("League")] 
        [Display(Name = "ID de Liga")] public int LeagueId { get; set; }
        
        [Required]
        [Display(Name = "Nome")]
        public string Name { get; set; }
        [Required][StringLength(3)]
        [Display(Name = "Iniciais")]
        public string Initials { get; set; }

        [Display(Name = "Data de Fundação")]
        public DateTime FoundingDate { get; set; }
        [Range(0, int.MaxValue)][Required]
        [Display(Name = "Número de titulos")]
        public int NumberOfTitles { get; set; }

        [Display(Name = "Cor principal")]
        public string MainColor { get; set; }

        [Display(Name = "Jogadores")]
        public List<Player> Players { get; set; }

        [Display(Name = "Liga",Order = -9)]
        public League League { get; set; }

    }
}
