package ar.edu.unq.obj3.O3_TP1.actividad

import ar.edu.unq.obj3.O3_TP1.persona.Persona
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import java.util.Date

abstract class Actividad( val nombre : String,
		val costo : Int,
		val fechaPresentacion : Date,
		val responsable : Persona,
		val otros : Persona* ) {

	var fechaAprobacion : Date = null
	var integrantes : Set[Persona] = Set()

	integrantes += responsable
	integrantes ++= otros

	def agregarEnAgenda( agenda : Agenda ) : Agenda

}
case class Seminario( override val nombre : String, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* ) {

	val sesiones : Set[Sesion] = Set()

	def crearSesion( fecha : Date, horaInicio : Int, horaFin : Int ) {
		sesiones += new Sesion( fecha, horaInicio, horaFin, this )
	}

	override def agregarEnAgenda( agenda : Agenda ) : Agenda = {
		this.sesiones.foldRight( agenda )( ( s, a ) => s.agregarEnAgenda( a ) )
	}

}
case class Charla( override val nombre : String, val fecha : Date, val horaInicio : Int, val horaFin : Int, val publico : Int, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* ) {
	override def agregarEnAgenda( agenda : Agenda ) : Agenda = {
		agenda.agregarEntrada( this.fecha, this.horaInicio, this.horaFin, this.nombre )
	}

}
case class Proyecto( override val nombre : String, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* ) {

	val bitacora : Set[Experimento] = Set()
	val resultados : Map[String, Date] = Map()

	def articulos = {
		resultados.filter( { case ( r, s ) => r contains "articulo" } )
	}

	def agregarArticulos( m : Map[String, Date] ) : Map[String, Date] = {
		articulos.foldRight( m )( ( a, m ) => { m += a; m } )
	}
	def agregarResultado( descripcion : String, fecha : Date ) {
		resultados += ( ( descripcion, fecha ) )
	}

	def agregarEnBitacora( fecha : Date, horaDesde : Int, horaFin : Int, descripcion : String ) {
		bitacora += ( new Experimento( fecha, horaDesde, horaFin, descripcion ) )
	}
	override def agregarEnAgenda( agenda : Agenda ) : Agenda = {
		this.bitacora.foldRight( agenda )( ( e, a ) => e.agregarEnAgenda( a ) )
	}
}