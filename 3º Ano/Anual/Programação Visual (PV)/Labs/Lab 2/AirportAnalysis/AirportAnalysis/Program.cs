using System;
using System.Collections.Generic;
using System.Linq;

namespace AirportAnalysis
{
    public class Program
    {
        public static void Main(string[] args)
        {
            List<Airport> airports = Airports.Get();

            /* Console.WriteLine("Lista de aeroportos: \n");
            foreach (Airport airport in airports)
            {
                Console.WriteLine($"{airport.AirportId:00} - {airport.AirportICAO}");
            } */

            // Testes LinQ
            /* 1.1
            List<string> orderderAirports = (from air in airports where air.IsSchengen == true orderby air.AirportICAO select air.AirportICAO).ToList();
            foreach (string s in orderderAirports)
            {
                Console.WriteLine(s);
            } */
            /* 1.2
            List<string> startsWithL = (from air in airports where air.AirportICAO.StartsWith("L") select air.AirportICAO).ToList();
            foreach (string s in startsWithL)
            {
                Console.WriteLine(s);
            } */
            /* 1.3
            List<string> startsWithLAndSchengen = (from air in airports where air.AirportICAO.StartsWith("L") && air.IsSchengen == true select air.AirportICAO).ToList();
            foreach (string s in startsWithLAndSchengen)
            {
                Console.WriteLine(s);
            } */
            /* 2.1
            List<string> ukflightsOver5k = (from air in airports where air.Country == "United Kingdom" && air.DailyFlightAverage > 5000 select air.AirportICAO).ToList();
            foreach (string s in ukflightsOver5k)
            {
                Console.WriteLine(s);
            }*/
            /* 2.2 && 2.3
            airports.Add(new Airport { AirportId = 46, AirportICAO = "EGCC", Country = "United Kingdom", City = "MANCHESTER", IsSchengen = true, DailyFlightAverage = 20000 });
            airports.Add(new Airport { AirportId = 47, AirportICAO = "EGJJ", Country = "United Kingdom", City = "JERSEY", IsSchengen = true, DailyFlightAverage = 10000 });
            List<string> ukflightsOver5k = (from air in airports where air.Country == "United Kingdom" && air.DailyFlightAverage > 5000 select air.AirportICAO).ToList();
            foreach (string s in ukflightsOver5k)
            {
                Console.WriteLine(s);
            } */
            /* 2.4 
            var maximum = (from air in airports select air.DailyFlightAverage).Max();
            List<string> test = (from air2 in airports where air2.DailyFlightAverage == maximum select air2.AirportICAO).ToList();
            foreach (string s in test)
            {
                Console.WriteLine(s);
            } */
            /* 3
            var flightsByCountry = (from air in airports group air.DailyFlightAverage by air.Country into ar 
                                             select new { Country = ar.Key, DailyFlights = ar.Sum() });
            foreach(var a in flightsByCountry)
            {
                Console.WriteLine(a);
            }
            var maximumNumber = (from air2 in flightsByCountry select air2.DailyFlights).Max();
            var minimumNumber = (from air3 in flightsByCountry select air3.DailyFlights).Min();
            var countryWithMost = (from ar in flightsByCountry where ar.DailyFlights == maximumNumber select ar.Country);
            var countryWithLeast = (from ar2 in flightsByCountry where ar2.DailyFlights == minimumNumber select ar2.Country);
            foreach(var a2 in countryWithMost)
            {
                Console.WriteLine("Country with most flights -> " + a2);
            }
            foreach(var a3 in countryWithLeast)
            {
                Console.WriteLine("Country with least flights -> " + a3);
            }
            */
            



        }
    }
}
