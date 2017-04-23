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
    public class SkladnikiWSpizarniController : ApiController
    {
        private DB_A172D4_slowo2017Entities db = new DB_A172D4_slowo2017Entities();

        // GET: api/SkladnikiWSpizarni
        public IQueryable<SkladnikiWSpizarni> GetSkladnikiWSpizarni(int id)
        {
            return db.SkladnikiWSpizarni.Where(s => s.idSpizarnia == id);
        }

        //// GET: api/SkladnikiWSpizarni/5
        //[ResponseType(typeof(SkladnikiWSpizarni))]
        //public IHttpActionResult GetSkladnikiWSpizarni(int id)
        //{
        //    SkladnikiWSpizarni skladnikiWSpizarni = db.SkladnikiWSpizarni.Find(id);
        //    if (skladnikiWSpizarni == null)
        //    {
        //        return NotFound();
        //    }

        //    return Ok(skladnikiWSpizarni);
        //}

        // PUT: api/SkladnikiWSpizarni/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutSkladnikiWSpizarni(int id, SkladnikiWSpizarni skladnikiWSpizarni)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != skladnikiWSpizarni.idSkladnikSpiz)
            {
                return BadRequest();
            }

            db.Entry(skladnikiWSpizarni).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SkladnikiWSpizarniExists(id))
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

        // POST: api/SkladnikiWSpizarni
        [ResponseType(typeof(SkladnikiWSpizarni))]
        public IHttpActionResult PostSkladnikiWSpizarni(SkladnikiWSpizarni skladnikiWSpizarni)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.SkladnikiWSpizarni.Add(skladnikiWSpizarni);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = skladnikiWSpizarni.idSkladnikSpiz }, skladnikiWSpizarni);
        }

        // DELETE: api/SkladnikiWSpizarni/5
        [ResponseType(typeof(SkladnikiWSpizarni))]
        public IHttpActionResult DeleteSkladnikiWSpizarni(int id)
        {
            SkladnikiWSpizarni skladnikiWSpizarni = db.SkladnikiWSpizarni.Find(id);
            if (skladnikiWSpizarni == null)
            {
                return NotFound();
            }

            db.SkladnikiWSpizarni.Remove(skladnikiWSpizarni);
            db.SaveChanges();

            return Ok(skladnikiWSpizarni);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool SkladnikiWSpizarniExists(int id)
        {
            return db.SkladnikiWSpizarni.Count(e => e.idSkladnikSpiz == id) > 0;
        }
    }
}