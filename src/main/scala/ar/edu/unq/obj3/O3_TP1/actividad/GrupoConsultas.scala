package ar.edu.unq.obj3.O3_TP1.actividad

import ar.edu.unq.obj3.O3_TP1.grupos.GrupoDeInvestigacion
import ar.edu.unq.obj3.O3_TP1.persona.Persona
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import ar.edu.unq.obj3.O3_TP1.grupos.GrupoDeInvestigacion
import java.util.Date

case class Agenda() {
	class AgendaEntry( val fecha : Date, val horaInicio : Int, val horaFin : Int, val descripcion : String )
	def entries : Set[AgendaEntry] = Set()

	def agregarEntrada( fecha : Date, horaInicio : Int, horaFin : Int, descripcion : String ) : Agenda = {
		entries += new AgendaEntry( fecha, horaInicio, horaFin, descripcion )
		return this;
	}
}

object GrupoConsultas {

	def sinAprobar( grupo : GrupoDeInvestigacion ) {
		grupo.actividades.filter( ( g ) => g.fechaAprobacion == null )
	}
	def costoTotal( grupo : GrupoDeInvestigacion ) {
		grupo.actividades.foldRight( 0 )( ( a, ac ) => ac + a.costo )
	}

	def articulos( grupo : GrupoDeInvestigacion ) {
		val proyectos = grupo.actividades collect { case p : Proyecto => p }
		val articulos = proyectos.map( { ( p ) => p.articulos } )
		proyectos.foldRight( Map[String, Date]() )( ( p, m ) => p.agregarArticulos( m ) )
	}
	def agenda( persona : Persona ) : Agenda = {
		persona.grupos.foldRight( Agenda() )( ( g, a ) => g.actividades.foldRight( a )( ( ac, a ) => ac.agregarEnAgenda( a ) ) )
	}
}