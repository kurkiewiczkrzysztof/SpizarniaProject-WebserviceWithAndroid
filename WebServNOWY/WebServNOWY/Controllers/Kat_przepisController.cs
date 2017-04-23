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
    public class Kat_przepisController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/Kat_przepis
        public IQueryable<Kat_przepis> GetKat_przepis()
        {
            return db.Kat_przepis;
        }

        // GET: api/Kat_przepis/5
        [ResponseType(typeof(Kat_przepis))]
        public IHttpActionResult GetKat_przepis(int id)
        {
            Kat_przepis kat_przepis = db.Kat_przepis.Find(id);
            if (kat_przepis == null)
            {
                return NotFound();
            }

            return Ok(kat_przepis);
        }

        // PUT: api/Kat_przepis/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutKat_przepis(int id, Kat_przepis kat_przepis)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != kat_przepis.idKat_przepis)
            {
                return BadRequest();
            }

            db.Entry(kat_przepis).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!Kat_przepisExists(id))
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

        // POST: api/Kat_przepis
        [ResponseType(typeof(Kat_przepis))]
        public IHttpActionResult PostKat_przepis(Kat_przepis kat_przepis)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Kat_przepis.Add(kat_przepis);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = kat_przepis.idKat_przepis }, kat_przepis);
        }

        // DELETE: api/Kat_przepis/5
        [ResponseType(typeof(Kat_przepis))]
        public IHttpActionResult DeleteKat_przepis(int id)
        {
            Kat_przepis kat_przepis = db.Kat_przepis.Find(id);
            if (kat_przepis == null)
            {
                return NotFound();
            }

            db.Kat_przepis.Remove(kat_przepis);
            db.SaveChanges();

            return Ok(kat_przepis);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool Kat_przepisExists(int id)
        {
            return db.Kat_przepis.Count(e => e.idKat_przepis == id) > 0;
        }
    }
}