using ESTeSoccerClient;
using ESTeSoccerMVC.Models;
using System.Net;
using System.Net.Http.Headers;

class Program
{
    static HttpClient client = new HttpClient();

    static async Task<List<League>> GetLeaguesAsync(string path)
    {
        List<League> leagues = null;
        HttpResponseMessage response = await client.GetAsync(path);
        if (response.IsSuccessStatusCode)
        {
            leagues = await response.Content.ReadAsAsync<List<League>>();
        }

        return leagues;
    }


    
    // implementar aqui os metodos assincronos: 
    static async Task<League> GetLeagueAsync(string path, int id)
    {
        League league = null;
        HttpResponseMessage response = await client.GetAsync(path + $"/{id}");
        if (response.IsSuccessStatusCode)
        {
            league = await response.Content.ReadAsAsync<League>();
        }

        return league;
    }
  
    static async Task<HttpStatusCode> CreateLeagueAsync(League League)
    {
        HttpResponseMessage response = await client.PostAsJsonAsync($"api/LeaguesApi", League);
        return response.StatusCode;
    }
    //static async Task<HttpStatusCode> UpdateLeagueAsync(League League)
    static async Task<HttpStatusCode> DeleteLeagueAsync(int id)
    {
        HttpResponseMessage response = await client.DeleteAsync($"api/LeaguesApi/{id}");
        return response.StatusCode;
    }


public static void Main(string[] args)
    {
        // coloque o porto utilizado no seu projecto 
        // ver na barra de endereço do browser ou dentro de launchSettings.json
        client.BaseAddress = new Uri("https://localhost:7040");
        client.DefaultRequestHeaders.Accept.Clear();
        client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/json"));

        League league = null;
        List<League> leagues;
        int id;
        int option;

        do
        {
            Console.WriteLine();
            Console.WriteLine("[ 1 ] Consultar todas as ligas");
            Console.WriteLine("[ 2 ] Consultar uma liga por id");
            Console.WriteLine("[ 3 ] Adicionar liga");
            Console.WriteLine("[ 4 ] Alterar liga");
            Console.WriteLine("[ 5 ] Apagar liga");
            Console.WriteLine("[ 0 ] Sair do Programa");
            Console.WriteLine("-------------------------------------");
            Console.Write("Digite uma opção: ");
            option = Int32.Parse(Console.ReadLine());

            switch (option)
            {
                case 1:
                    leagues = GetLeaguesAsync($"api/LeaguesApi").GetAwaiter().GetResult();
                    if (leagues != null)
                         Console.WriteLine("Ligas: {0}", leagues.LeaguesList());
                    break;
                case 2:
                    Console.Write("Id=");
                    id = Int32.Parse(Console.ReadLine());

                    /*  completar neste espaço  */
                    league = GetLeagueAsync($"api/LeaguesApi", id).GetAwaiter().GetResult();

                    if (league != null)
                        Console.WriteLine("Liga: {0}", league.Name);
                    break;
                case 3:
                    league = new League();
                    Console.Write("Nome da liga=");
                    league.Name = Console.ReadLine();
                    Console.Write("Pais=");
                    league.Country = Console.ReadLine();
                    league.Teams = new List<Team>();
                    var httpCode = CreateLeagueAsync(league).GetAwaiter().GetResult();
                    Console.WriteLine(httpCode);

                    // Console.WriteLine("status: {0}", result);
                    break;
                case 4:
                    Console.Write("Nome antigo: ");
                    String nomeAntigo = Console.ReadLine();
                    Console.Write("Nome novo  : ");
                    String nomeNovo = Console.ReadLine();

                    /*     completar neste espaço   */


                    break;
                case 5:
                    Console.Write("Apagar liga Id=");
                    id = Int32.Parse(Console.ReadLine());

                    /*     completar neste espaço   */
                    var http = DeleteLeagueAsync(id).GetAwaiter().GetResult();
                    Console.WriteLine(http);
                    break;
                default:
                    break;
            }

        }
        while (option != 0);
    }
}