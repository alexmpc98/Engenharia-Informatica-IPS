// See https://aka.ms/new-console-template for more information
// Console.WriteLine("Hello, World!");

using ESTeSoccerClient;
using ESTeSoccerMVC.Models;

public class Program
{

    public static void Main(string[] args)
    {
        ESTeSoccerClientUtils.SetClient();
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
            Console.WriteLine("[ 6 ] Consultar clubes de uma liga");
            Console.WriteLine("[ 0 ] Sair do Programa");
            Console.WriteLine("-------------------------------------");
            Console.Write("Digite uma opção: ");
            option = Int32.Parse(Console.ReadLine());

            switch (option)
            {
                case 1:
                    leagues = ESTeSoccerClientUtils.GetLeaguesAsync($"api/LeaguesApi").GetAwaiter().GetResult();
                    if (leagues != null)
                        Console.WriteLine("Ligas: {0}", leagues.LeaguesList());
                    break;
                case 2:
                    Console.Write("Id=");
                    id = Int32.Parse(Console.ReadLine());

                    league = ESTeSoccerClientUtils.GetLeagueAsync($"api/LeaguesApi/{id}").GetAwaiter().GetResult();


                    if (league != null)
                        Console.WriteLine("Liga: {0}", league.Name);
                    break;
                case 3:
                    league = new League();
                    Console.Write("Nome da liga=");
                    league.Name = Console.ReadLine();
                    Console.Write("Nome do país=");
                    league.Country = Console.ReadLine();

                    var result = ESTeSoccerClientUtils.CreateLeagueAsync(league).GetAwaiter().GetResult();

                    Console.WriteLine("status: {0}", result);
                    break;
                case 4:
                    Console.Write("Nome antigo: ");
                    String nomeAntigo = Console.ReadLine();
                    Console.Write("Nome novo  : ");
                    String nomeNovo = Console.ReadLine();

                    leagues = ESTeSoccerClientUtils.GetLeaguesAsync($"api/leaguesapi").GetAwaiter().GetResult();
                    league = leagues.FirstOrDefault(m => m.Name.Contains(nomeAntigo));
                    if (league != null)
                    {
                        league.Name = nomeNovo;
                        league.Teams = new List<Team>();
                        var status2 = ESTeSoccerClientUtils.UpdateLeagueAsync(league).GetAwaiter().GetResult();
                        Console.WriteLine("status: {0}", status2);
                    }
                    else
                        Console.WriteLine("A liga {0} não existe", nomeAntigo);


                    break;

                case 5:
                    Console.Write("Apagar liga Id=");
                    id = Int32.Parse(Console.ReadLine());

                    var status = ESTeSoccerClientUtils.DeleteLeagueAsync(id).GetAwaiter().GetResult();
                    Console.WriteLine("status: {0}", status);

                    break;

                case 6:
                    Console.Write("Id=");
                    id = Int32.Parse(Console.ReadLine());

                    league = ESTeSoccerClientUtils.GetLeagueAsync($"api/LeaguesApi/LeagueAndTeams/{id}").GetAwaiter().GetResult();

                    if (league != null)
                    {
                        Console.WriteLine($"Liga: {league.LeagueId} - {league.Name}");
                        if (league.Teams != null)
                        {
                            foreach (var team in league.Teams)
                            {
                                Console.WriteLine($"\t{team.Name} ({team.Initials})");
                            }
                        }
                    }

                    break;
                default:
                    break;
            }

        }
        while (option != 0);
    }
}
