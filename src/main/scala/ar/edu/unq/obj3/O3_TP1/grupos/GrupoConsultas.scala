package ar.edu.unq.obj3.O3_TP1.grupos

import java.util.Date
import scala.collection.mutable.Map
import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.actividad.Proyecto
import ar.edu.unq.obj3.O3_TP1.persona.Persona
import ar.edu.unq.obj3.O3_TP1.actividad.Actividad

object GrupoConsultas {

	def sinAprobar( grupo : GrupoDeInvestigacion ) : Set[Actividad] =
		grupo.actividades.filter { g => g.fechaAprobacion == null }

	def costoTotal( grupo : GrupoDeInvestigacion ) : Int = {
		grupo.actividades.foldRight( 0 )( ( a, ac ) => ac + a.costo )
	}

	def articulos( grupo : GrupoDeInvestigacion ) : Map[String, Date] = {
		( grupo.actividades collect { case p : Proyecto => p.articulos } ).reduce { ( a1, a2 ) => a1 ++= a2 }
	}

	def agenda( persona : Persona ) : Agenda = {
		val agenda = Agenda()
		val actividades = ( persona.grupos collect { case g : GrupoDeInvestigacion => g.actividades } ).reduce { ( a1, a2 ) => a1 ++= a2 }
		actividades.foreach { actividad =>
			actividad.agregarEnAgenda( agenda )
		}
		return agenda
	}
}