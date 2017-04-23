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
    public class PrzepisController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/Przepis
        public IQueryable<Przepis> GetPrzepis(int id)
        {
            return db.Przepis.Where(s => s.idKat_przepis == id);
        }

        // GET: api/Przepis/5
        //[ResponseType(typeof(Przepis))]
        //public IHttpActionResult GetPrzepis(int id)
        //{
        //    Przepis przepis = db.Przepis.Find(id);
        //    if (przepis == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(przepis);
        //}

        // PUT: api/Przepis/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutPrzepis(int id, Przepis przepis)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != przepis.idPrzepis)
            {
                return BadRequest();
            }

            db.Entry(przepis).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!PrzepisExists(id))
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

        // POST: api/Przepis
        [ResponseType(typeof(Przepis))]
        public IHttpActionResult PostPrzepis(Przepis przepis)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Przepis.Add(przepis);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = przepis.idPrzepis }, przepis);
        }

        // DELETE: api/Przepis/5
        [ResponseType(typeof(Przepis))]
        public IHttpActionResult DeletePrzepis(int id)
        {
            Przepis przepis = db.Przepis.Find(id);
            if (przepis == null)
            {
                return NotFound();
            }

            db.Przepis.Remove(przepis);
            db.SaveChanges();

            return Ok(przepis);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool PrzepisExists(int id)
        {
            return db.Przepis.Count(e => e.idPrzepis == id) > 0;
        }
    }
}