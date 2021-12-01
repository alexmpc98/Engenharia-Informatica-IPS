using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace ESTeSoccer.Models
{
    public class Player
    {
        [Key] [Display(Name = "ID de Jogador")]  public int Playerid { get; set; }

        //[ForeignKey("Team")]
        [Display(Name = "Id de Equipa")]
        public int TeamId { get; set; }
        [Required]
        [Display(Name = "Nome")]
        public string Name { get; set; }
        [Required]
        [Display(Name = "Data de Nascimento")]
        public DateTime DateOfBirth { get; set; }
        [Range(0, int.MaxValue)][Required][Display(Name = "Valor de Mercado")]
        public double MarketValue { get; set; }

        [Display(Name = "Equipa",Order = -9)]
        public Team Team { get; set; }

    }
}
