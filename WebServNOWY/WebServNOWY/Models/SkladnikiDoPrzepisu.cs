//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace WebServNOWY.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class SkladnikiDoPrzepisu
    {
        public int idSkladnikPrzepis { get; set; }
        public double ilosc { get; set; }
        public int idPrzepis { get; set; }
        public int idZywnosc { get; set; }
    
        public virtual Przepis Przepis { get; set; }
        public virtual Zywnosc Zywnosc { get; set; }
    }
}
