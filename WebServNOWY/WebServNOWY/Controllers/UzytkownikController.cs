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
    public class UzytkownikController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/Uzytkownik
        public IQueryable<Uzytkownik> GetUzytkownik()
        {
            return db.Uzytkownik;
        }

        // GET: api/Uzytkownik/5
        [ResponseType(typeof(Uzytkownik))]
        public IHttpActionResult GetUzytkownik(int id)
        {
            Uzytkownik uzytkownik = db.Uzytkownik.Find(id);
            if (uzytkownik == null)
            {
                return NotFound();
            }

            return Ok(uzytkownik);
        }

        // PUT: api/Uzytkownik/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutUzytkownik(int id, Uzytkownik uzytkownik)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != uzytkownik.idUzytkownik)
            {
                return BadRequest();
            }

            db.Entry(uzytkownik).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!UzytkownikExists(id))
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

        // POST: api/Uzytkownik
        [ResponseType(typeof(Uzytkownik))]
        public IHttpActionResult PostUzytkownik(Uzytkownik uzytkownik)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Uzytkownik.Add(uzytkownik);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = uzytkownik.idUzytkownik }, uzytkownik);
        }

        // DELETE: api/Uzytkownik/5
        [ResponseType(typeof(Uzytkownik))]
        public IHttpActionResult DeleteUzytkownik(int id)
        {
            Uzytkownik uzytkownik = db.Uzytkownik.Find(id);
            if (uzytkownik == null)
            {
                return NotFound();
            }

            db.Uzytkownik.Remove(uzytkownik);
            db.SaveChanges();

            return Ok(uzytkownik);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool UzytkownikExists(int id)
        {
            return db.Uzytkownik.Count(e => e.idUzytkownik == id) > 0;
        }
    }
}