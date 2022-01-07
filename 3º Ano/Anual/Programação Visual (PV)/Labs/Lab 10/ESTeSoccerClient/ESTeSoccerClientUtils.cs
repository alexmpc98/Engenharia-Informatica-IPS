using ESTeSoccerMVC.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ESTeSoccerClient
{
    public static class ESTeSoccerClientUtils
    {

        public static string LeaguesList(this List<League> leagues)
        {
            StringBuilder stb = new StringBuilder("{");
            String comma = "";
            foreach (League league in leagues)
            {
                stb.Append(comma);
                stb.Append(league.LeagueId);
                stb.Append("-");
                stb.Append(league.Name);
                stb.Append(", ");
                stb.Append(league.Country);
                comma = (comma == "") ? ", " : comma; // ou mais simples comma=", ";
            }
            stb.Append("}");
            return stb.ToString();
        }
    }
}
