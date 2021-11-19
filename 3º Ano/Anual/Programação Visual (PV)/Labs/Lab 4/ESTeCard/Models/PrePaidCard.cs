using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ESTeCard.Models
{
    public class PrePaidCard
    {
        public long id { get; set; }
        public string name { get; set; } 
        public string entity { get; set; }
        public int code
        {
            get; set;
        }
        public double credit
        {
            get; set;
        }

        
    }
}
