using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using WebServNOWY.Models;

namespace WebServNOWY.Controllers
{
    public class SpizarniaController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        public IHttpActionResult zwrocwiersz(int id)
        {
            var balance = from a in db.Spizarnia
                          join c in db.SkladnikiWSpizarni on a.idSpizarnia equals c.idSpizarnia
                          join d in db.Zywnosc on c.idZywnosc equals d.idZywnosc
                          join e in db.Kat_Zywnosc on d.idkat_zywnosc equals e.idkat_zywnosc
                          where a.idSpizarnia == id
                          select new
                          {
                              idSpiz = c.idSkladnikSpiz,
                              Ilosc = c.Ilosc,
                              Nazwa = d.Nazwa,
                              nazwaKat = e.Nazwa_kat
                          };

            return Ok(balance);
        }

        //// GET: api/Spizarnia
        //public IQueryable<Spizarnia> GetSpizarnia()
        //{
        //    return db.Spizarnia;
        //}

        //// GET: api/Spizarnia/5
        //[ResponseType(typeof(Spizarnia))]
        //public IHttpActionResult GetSpizarnia(int id)
        //{
        //    Spizarnia spizarnia = db.Spizarnia.Find(id);
        //    if (spizarnia == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(spizarnia);
        //}

        // PUT: api/Spizarnia/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSpizarnia(int id, Spizarnia spizarnia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != spizarnia.idSpizarnia)
            {
                return BadRequest();
            }

            db.Entry(spizarnia).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SpizarniaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Spizarnia
        [ResponseType(typeof(Spizarnia))]
        public IHttpActionResult PostSpizarnia(Spizarnia spizarnia)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Spizarnia.Add(spizarnia);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = spizarnia.idSpizarnia }, spizarnia);
        }

        // DELETE: api/Spizarnia/5
        [ResponseType(typeof(Spizarnia))]
        public IHttpActionResult DeleteSpizarnia(int id)
        {
            Spizarnia spizarnia = db.Spizarnia.Find(id);
            if (spizarnia == null)
            {
                return NotFound();
            }

            db.Spizarnia.Remove(spizarnia);
            db.SaveChanges();

            return Ok(spizarnia);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SpizarniaExists(int id)
        {
            return db.Spizarnia.Count(e => e.idSpizarnia == id) > 0;
        }
    }
}