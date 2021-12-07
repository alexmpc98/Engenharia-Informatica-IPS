using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;


namespace MiniESTS.Models
{
    public class Cliente : IdentityUser
    {
        public string name;
    }
}
