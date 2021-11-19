using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ESTeCard.Models.Data
{
    public class EstCards
    {
        static List<PrePaidCard> cards = new List<PrePaidCard>() 
        { 
        new PrePaidCard() 
        { id = 1234567887654321, name = "José Martins" , entity = "ESTeBank" , code = 5123, credit = 320.0 },
        new PrePaidCard()
        {  id = 3536776512871456, name = "Carlos Santos" , entity = "ESTeBank" , code = 4141, credit = 30.0 },
        new PrePaidCard() 
        { id = 7786209856342765, name = "Rui Rosa" , entity = "ESTeBank" , code = 2723, credit = 5230.0 }
        };

        public static List<PrePaidCard> ESTeCards() => cards;
    }
}
