using Microsoft.AspNetCore.Identity;

namespace MiniESTS.Models
{
    public class Cliente : IdentityUser
    {
        public string Nome { get; set; }
    }
}
