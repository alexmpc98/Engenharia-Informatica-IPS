using ESTeSoccerMVC.Models;
using System.Net;
using System.Net.Http.Headers;
using System.Text;

namespace ESTeSoccerClient
{
    public static class ESTeSoccerClientUtils
    {
        static HttpClient client;

        public static void SetClient()
        {
            client = new HttpClient();
            client.BaseAddress = new Uri("http://localhost:5041/");
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(
                new MediaTypeWithQualityHeaderValue("application/json"));
        }
        public static void Dispose()
        {
            client.Dispose();
        }

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


        public static async Task<List<League>> GetLeaguesAsync(string path)
        {
            List<League> leagues = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                leagues = await response.Content.ReadAsAsync<List<League>>();
            }

            return leagues;
        }

        public static async Task<League> GetLeagueAsync(string path)
        {
            League league = null;
            HttpResponseMessage response = await client.GetAsync(path);
            if (response.IsSuccessStatusCode)
            {
                league = await response.Content.ReadAsAsync<League>();
            }

            return league;
        }

        public static async Task<HttpStatusCode> CreateLeagueAsync(League league)
        {
            HttpResponseMessage response = await client.PostAsJsonAsync(
                "api/LeaguesApi", league);

            // return URI of the created resource.
            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> UpdateLeagueAsync(League league)
        {
            HttpResponseMessage response = await client.PutAsJsonAsync(
                $"api/leaguesapi/{league.LeagueId}", league);

            return response.StatusCode;
        }

        public static async Task<HttpStatusCode> DeleteLeagueAsync(int id)
        {
            HttpResponseMessage response = await client.DeleteAsync(
                $"api/leaguesapi/{id}");
            return response.StatusCode;
        }
    }
}
