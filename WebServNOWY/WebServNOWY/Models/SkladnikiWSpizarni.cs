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
    using Newtonsoft.Json;
    using System;
    using System.Collections.Generic;
    
    public partial class SkladnikiWSpizarni
    {
        public int idSkladnikSpiz { get; set; }
        public Nullable<double> Ilosc { get; set; }
        public Nullable<int> idSpizarnia { get; set; }
        public Nullable<int> idZywnosc { get; set; }
        [JsonIgnore]
        public virtual Spizarnia Spizarnia { get; set; }
        [JsonIgnore]
        public virtual Zywnosc Zywnosc { get; set; }
    }
}
