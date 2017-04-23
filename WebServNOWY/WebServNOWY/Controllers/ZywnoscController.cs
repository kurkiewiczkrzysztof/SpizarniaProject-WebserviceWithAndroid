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
    public class ZywnoscController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/Zywnosc
        public IQueryable<Zywnosc> GetZywnosc(int id)
        {
            return db.Zywnosc.Where(s => s.idkat_zywnosc == id);
        }

        //// GET: api/Zywnosc/5
        //[ResponseType(typeof(Zywnosc))]
        //public IHttpActionResult GetZywnosc(int id)
        //{
        //    Zywnosc zywnosc = db.Zywnosc.Find(id);
        //    if (zywnosc == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(zywnosc);
        //}

        // PUT: api/Zywnosc/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutZywnosc(int id, Zywnosc zywnosc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != zywnosc.idZywnosc)
            {
                return BadRequest();
            }

            db.Entry(zywnosc).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!ZywnoscExists(id))
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

        // POST: api/Zywnosc
        [ResponseType(typeof(Zywnosc))]
        public IHttpActionResult PostZywnosc(Zywnosc zywnosc)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Zywnosc.Add(zywnosc);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = zywnosc.idZywnosc }, zywnosc);
        }

        // DELETE: api/Zywnosc/5
        [ResponseType(typeof(Zywnosc))]
        public IHttpActionResult DeleteZywnosc(int id)
        {
            Zywnosc zywnosc = db.Zywnosc.Find(id);
            if (zywnosc == null)
            {
                return NotFound();
            }

            db.Zywnosc.Remove(zywnosc);
            db.SaveChanges();

            return Ok(zywnosc);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool ZywnoscExists(int id)
        {
            return db.Zywnosc.Count(e => e.idZywnosc == id) > 0;
        }
    }
}