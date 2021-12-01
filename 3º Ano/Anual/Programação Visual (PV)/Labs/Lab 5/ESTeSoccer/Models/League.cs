using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.ComponentModel.DataAnnotations;

namespace ESTeSoccer.Models
{
    public class League
    {
        [Key]
        [Display(Name = "Id de Liga")]
        public int LeagueId { get; set; }

        [Required]
        [StringLength(20)]
        [Display(Name = "Nome")]
        public String Name { get; set; }

        [Required]
        [Display(Name = "Pais")]
        public String Country { get; set; }

        [Display(Name = "Equipas")]
        public List<Team> Teams { get; set; }

        

    }
}
