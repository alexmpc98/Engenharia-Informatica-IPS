using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AirportAnalysis
{
    public class Airport
    {
        public int AirportId { get; set; }
        public string AirportICAO { get; set; }
        public string Country { get; set; }
        public string City { get; set; }
        public bool IsSchengen { get; set; }
        public int DailyFlightAverage { get; set; }


        public override string ToString()
        {
            return $"{AirportId:00} - {AirportICAO} - {Country} - {City} \t {IsSchengen} \t {DailyFlightAverage}";
        }
    }
}
