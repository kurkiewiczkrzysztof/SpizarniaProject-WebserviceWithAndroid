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
    
    public partial class Przepis
    {
        public Przepis()
        {
            this.SkladnikiDoPrzepisu = new HashSet<SkladnikiDoPrzepisu>();
        }
    
        public int idPrzepis { get; set; }
        public string Nazwa { get; set; }
        public string opis { get; set; }
        public int idKat_przepis { get; set; }
    
        public virtual Kat_przepis Kat_przepis { get; set; }
        public virtual ICollection<SkladnikiDoPrzepisu> SkladnikiDoPrzepisu { get; set; }
    }
}