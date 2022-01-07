using System.ComponentModel.DataAnnotations;

namespace ESTeSoccerMVC.Models
{
    public class League
    {

    //    public League ()
    //    {
    //        Teams = new List<Team> ();
    //    }

        [Display(Name = "Id")]
        public int LeagueId { get; set; }

        [Required]
        [MaxLength(20)]
        [Display(Name = "Nome")]
        public string Name { get; set; }

        [Required]
        [MaxLength(20)]
        [Display(Name = "País")]
        public string Country { get; set; }

        public List<Team>? Teams { get; set; }
    }
}
