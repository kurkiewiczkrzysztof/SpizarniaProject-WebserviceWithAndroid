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
    
    public partial class Uzytkownik
    {
        public Uzytkownik()
        {
            this.Spizarnia = new HashSet<Spizarnia>();
        }
    
        public int idUzytkownik { get; set; }
        public string Login { get; set; }
        public string Haslo { get; set; }
    
        public virtual ICollection<Spizarnia> Spizarnia { get; set; }
    }
}