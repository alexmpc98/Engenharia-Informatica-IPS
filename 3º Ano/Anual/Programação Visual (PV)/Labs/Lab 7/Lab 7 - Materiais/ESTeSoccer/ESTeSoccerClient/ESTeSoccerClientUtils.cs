using ESTeSoccerMVC.Models;
using System;
using System.Collections.Generic;
using System.Text;


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

        public static string LeagueId(int id, List<League> leagues)
        {
            StringBuilder stb = new StringBuilder("{");
            String comma = "";
            foreach (League league in leagues)
            {
                if(league.LeagueId == id)
                {
                    stb.Append(comma);
                    stb.Append(league.LeagueId);
                    stb.Append("-");
                    stb.Append(league.Name);
                    stb.Append(", ");
                    stb.Append(league.Country);
                    comma = (comma == "") ? ", " : comma; // ou mais simples comma=", ";
                }
            }
            stb.Append("}");
            return stb.ToString();
        }
    }
}
