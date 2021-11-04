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

            ////////////////////////////////////////////////////--- Nivel 1 ---///////////////////////////////////////////

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

            ////////////////////////////////////////////////////--- Nivel 2 ---///////////////////////////////////////////

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

            ////////////////////////////////////////////////////--- Nivel 3 ---///////////////////////////////////////////

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

            ////////////////////////////////////////////////////--- Nivel 4 ---///////////////////////////////////////////

            /*4.1
            var airportOrdByCity = airports.OrderBy(ord => ord.City);
            foreach(var ordair in airportOrdByCity)
            {
                Console.WriteLine(ordair);
            }*/
            /*4.2
            var airportsWithoutRepeatedCountries = airports.Select(woutCountry => woutCountry.Country).Distinct();
            foreach(var withoutCountry in airportsWithoutRepeatedCountries)
            {
                Console.WriteLine(withoutCountry);
            } */
            /* 4.3
            bool inferiorDailyAverage = airports.Any(dAvg => dAvg.DailyFlightAverage < 5000);
            Console.WriteLine(inferiorDailyAverage);
            */
            /* 4.4 
            double averageFligthsAll = airports.Average(avg => avg.DailyFlightAverage);
            Console.WriteLine(averageFligthsAll);
            */

            ////////////////////////////////////////////////////--- Nivel 5 ---///////////////////////////////////////////

            /* 5.1
            var topFive = airports.OrderByDescending(x => x.DailyFlightAverage).
                Where(x => x.DailyFlightAverage < 100000 && x.IsSchengen != true).Take(5);
            foreach(var air in topFive)
            {
                Console.WriteLine(air);
            }
            */
            /* 5.2
            var topFiveSkip = airports.OrderByDescending(x => x.DailyFlightAverage).
                Where(x => x.DailyFlightAverage < 100000 && x.IsSchengen != true).Skip(5).Take(5);
            foreach(var air in topFiveSkip)
            {
                Console.WriteLine(air);
            }*/
            /* 5.3
            var germanAirports = airports.OrderBy(x => x.City).ThenBy(x => x.AirportICAO).
                Where(country => country.Country == "Germany");
            foreach(var gerAir in germanAirports)
            {
                Console.WriteLine(gerAir);
            } */
            /* 5.4
            var cityGermany = airports.Where(c => c.Country == "Germany")
                .GroupBy(ci => ci.City, (k , info) => new { City = k, AirportInfo = info.ToList() });
            
            
            foreach (var gerAirByCity in cityGermany)
            {
                Console.WriteLine("City of " + gerAirByCity.City + " : ");
                foreach (Airport airTest in gerAirByCity.AirportInfo)
                {
                    Console.WriteLine(airTest.AirportICAO  + " // "
                        + airTest.AirportId  + " // "
                        + "Is Schengen: " + airTest.IsSchengen + " // "
                        + airTest.DailyFlightAverage);
                }
            }
            */
        }
    }
}
