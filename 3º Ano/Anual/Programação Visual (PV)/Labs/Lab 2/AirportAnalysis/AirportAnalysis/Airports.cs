using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AirportAnalysis
{
    public static class Airports
    {

        public static List<Airport> Get()
        {
            Airport[] AirportList =
            {
                new Airport { AirportId = 1, AirportICAO = "LKJH", Country = "Czech Republic", City = "JINDRICHUV HRADEC", IsSchengen = true, DailyFlightAverage = 5108},
                new Airport { AirportId = 2, AirportICAO = "EHBD", Country = "Netherlands", City = "WEERT", IsSchengen = true, DailyFlightAverage = 37298},
                new Airport { AirportId = 3, AirportICAO = "EFSE", Country = "Finland", City = "SELANPAA", IsSchengen = true, DailyFlightAverage = 94915},
                new Airport { AirportId = 4, AirportICAO = "EBTX", Country = "Belgium", City = "VERVIERS", IsSchengen = true, DailyFlightAverage = 41561},
                new Airport { AirportId = 5, AirportICAO = "LIPB", Country = "Italy", City = "BOLZANO", IsSchengen = true, DailyFlightAverage = 53192},
                new Airport { AirportId = 6, AirportICAO = "LPMR", Country = "Portugal", City = "MONTE REAL", IsSchengen = true, DailyFlightAverage = 53773},
                new Airport { AirportId = 7, AirportICAO = "ESOH", Country = "Sweden", City = "HAGFORS", IsSchengen = true, DailyFlightAverage = 33016},
                new Airport { AirportId = 8, AirportICAO = "LDPV", Country = "Croatia", City = "VRSAR", IsSchengen = true, DailyFlightAverage = 9855},
                new Airport { AirportId = 9, AirportICAO = "EDPG", Country = "Germany", City = "GRIESAU", IsSchengen = true, DailyFlightAverage = 20587},
                new Airport { AirportId = 10, AirportICAO = "LKRO", Country = "Czech Republic", City = "ROUDNICE", IsSchengen = true, DailyFlightAverage = 21435},
                new Airport { AirportId = 11, AirportICAO = "EDLI", Country = "Germany", City = "BIELEFELD", IsSchengen = true, DailyFlightAverage = 84213},
                new Airport { AirportId = 12, AirportICAO = "LFPK", Country = "France", City = "COULOMMIERS", IsSchengen = true, DailyFlightAverage = 88642},
                new Airport { AirportId = 13, AirportICAO = "EDUF", Country = "Germany", City = "BIELEFELD", IsSchengen = true, DailyFlightAverage = 94148},
                new Airport { AirportId = 14, AirportICAO = "LFEF", Country = "France", City = "AMBOISE", IsSchengen = true, DailyFlightAverage = 88485},
                new Airport { AirportId = 15, AirportICAO = "EFKR", Country = "Finland", City = "KARSAMAKI", IsSchengen = true, DailyFlightAverage = 22225},
                new Airport { AirportId = 16, AirportICAO = "LKPR", Country = "Czech Republic", City = "PRAGUE", IsSchengen = true, DailyFlightAverage = 10786},
                new Airport { AirportId = 17, AirportICAO = "LKSB", Country = "Czech Republic", City = "STICHOVICE", IsSchengen = true, DailyFlightAverage = 52233},
                new Airport { AirportId = 18, AirportICAO = "ESKV", Country = "Sweden", City = "ARVIKA", IsSchengen = true, DailyFlightAverage = 15580},
                new Airport { AirportId = 19, AirportICAO = "LPPT", Country = "Portugal", City = "LISBOA", IsSchengen = true, DailyFlightAverage = 73140},
                new Airport { AirportId = 20, AirportICAO = "EDKL", Country = "Germany", City = "LEVERKUSEN", IsSchengen = true, DailyFlightAverage = 85867},
                new Airport { AirportId = 21, AirportICAO = "EDQY", Country = "Germany", City = "COBURG", IsSchengen = true, DailyFlightAverage = 82250},
                new Airport { AirportId = 22, AirportICAO = "LZMA", Country = "Slovakia", City = "MARTIN", IsSchengen = true, DailyFlightAverage = 86981},
                new Airport { AirportId = 23, AirportICAO = "ESNZ", Country = "Sweden", City = "ARE OSTERSUND", IsSchengen = true, DailyFlightAverage = 95959},
                new Airport { AirportId = 24, AirportICAO = "LETF", Country = "Spain", City = "VILLAMARTIN", IsSchengen = true, DailyFlightAverage = 71652},
                new Airport { AirportId = 25, AirportICAO = "ETNS", Country = "Germany", City = "BIELEFELD", IsSchengen = true, DailyFlightAverage = 3217},
                new Airport { AirportId = 26, AirportICAO = "LFMY", Country = "France", City = "SALON DE PROVENCE", IsSchengen = true, DailyFlightAverage = 36548},
                new Airport { AirportId = 27, AirportICAO = "ESSB", Country = "Sweden", City = "STOCKHOLM", IsSchengen = true, DailyFlightAverage = 44722},
                new Airport { AirportId = 28, AirportICAO = "LFAB", Country = "France", City = "DIEPPE", IsSchengen = true, DailyFlightAverage = 65988},
                new Airport { AirportId = 29, AirportICAO = "LJCE", Country = "Slovenia", City = "CERKLJE OB KRKI", IsSchengen = true, DailyFlightAverage = 19384},
                new Airport { AirportId = 30, AirportICAO = "EKGH", Country = "Denmark", City = "GRONHOLT", IsSchengen = true, DailyFlightAverage = 99981},
                new Airport { AirportId = 31, AirportICAO = "FMMS", Country = "Madagascar", City = "SAINTE-MARIE", IsSchengen = false, DailyFlightAverage = 69361},
                new Airport { AirportId = 32, AirportICAO = "CYGD", Country = "Canada", City = "GODERICH", IsSchengen = false, DailyFlightAverage = 91286},
                new Airport { AirportId = 33, AirportICAO = "LSGK", Country = "Switzerland", City = "SAANEN", IsSchengen = false, DailyFlightAverage = 31014},
                new Airport { AirportId = 34, AirportICAO = "RCTP", Country = "Taiwan", City = "TAIPEI", IsSchengen = false, DailyFlightAverage = 79166},
                new Airport { AirportId = 35, AirportICAO = "RI21", Country = "United States", City = "Newport", IsSchengen = false, DailyFlightAverage = 83795},
                new Airport { AirportId = 36, AirportICAO = "N26", Country = "United States", City = "PINEHURST", IsSchengen = false, DailyFlightAverage = 8529},
                new Airport { AirportId = 37, AirportICAO = "ZSQD", Country = "China", City = "QINGDAO", IsSchengen = false, DailyFlightAverage = 4001},
                new Airport { AirportId = 38, AirportICAO = "MYAF", Country = "Bahamas", City = "ANDROS TOWN", IsSchengen = false, DailyFlightAverage = 98425},
                new Airport { AirportId = 39, AirportICAO = "EGLA", Country = "United Kingdom", City = "BODMIN", IsSchengen = false, DailyFlightAverage = 37606},
                new Airport { AirportId = 40, AirportICAO = "35C", Country = "United States", City = "ROCKFORD", IsSchengen = false, DailyFlightAverage = 58498},
                new Airport { AirportId = 41, AirportICAO = "KHES", Country = "United States", City = "HEALDSBURG", IsSchengen = false, DailyFlightAverage = 33176},
                new Airport { AirportId = 42, AirportICAO = "KVBT", Country = "United States", City = "BENTONVILLE", IsSchengen = false, DailyFlightAverage = 96428},
                new Airport { AirportId = 43, AirportICAO = "ZBAA", Country = "China", City = "BEIJING", IsSchengen = false, DailyFlightAverage = 46167},
                new Airport { AirportId = 44, AirportICAO = "2GC", Country = "United States", City = "FAYETTEVILLE", IsSchengen = false, DailyFlightAverage = 63424},
                new Airport { AirportId = 45, AirportICAO = "YBWX", Country = "Australia", City = "BARROW I", IsSchengen = false, DailyFlightAverage = 81244}
            };

            return new List<Airport>(AirportList);
        }
    }
}
