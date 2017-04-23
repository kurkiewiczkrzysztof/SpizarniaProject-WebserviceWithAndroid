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
    public class Kat_ZywnoscController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/Kat_Zywnosc
        public IQueryable<Kat_Zywnosc> GetKat_Zywnosc()
        {
            return db.Kat_Zywnosc;
        }

        // GET: api/Kat_Zywnosc/5
        [ResponseType(typeof(Kat_Zywnosc))]
        public IHttpActionResult GetKat_Zywnosc(int id)
        {
            Kat_Zywnosc kat_Zywnosc = db.Kat_Zywnosc.Find(id);
            if (kat_Zywnosc == null)
            {
                return NotFound();
            }

            return Ok(kat_Zywnosc);
        }

        // PUT: api/Kat_Zywnosc/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutKat_Zywnosc(int id, Kat_Zywnosc kat_Zywnosc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != kat_Zywnosc.idkat_zywnosc)
            {
                return BadRequest();
            }

            db.Entry(kat_Zywnosc).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!Kat_ZywnoscExists(id))
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

        // POST: api/Kat_Zywnosc
        [ResponseType(typeof(Kat_Zywnosc))]
        public IHttpActionResult PostKat_Zywnosc(Kat_Zywnosc kat_Zywnosc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Kat_Zywnosc.Add(kat_Zywnosc);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = kat_Zywnosc.idkat_zywnosc }, kat_Zywnosc);
        }

        // DELETE: api/Kat_Zywnosc/5
        [ResponseType(typeof(Kat_Zywnosc))]
        public IHttpActionResult DeleteKat_Zywnosc(int id)
        {
            Kat_Zywnosc kat_Zywnosc = db.Kat_Zywnosc.Find(id);
            if (kat_Zywnosc == null)
            {
                return NotFound();
            }

            db.Kat_Zywnosc.Remove(kat_Zywnosc);
            db.SaveChanges();

            return Ok(kat_Zywnosc);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool Kat_ZywnoscExists(int id)
        {
            return db.Kat_Zywnosc.Count(e => e.idkat_zywnosc == id) > 0;
        }
    }
}