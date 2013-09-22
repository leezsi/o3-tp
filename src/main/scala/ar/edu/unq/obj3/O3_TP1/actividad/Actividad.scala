package ar.edu.unq.obj3.O3_TP1.actividad

import ar.edu.unq.obj3.O3_TP1.persona.Persona
import scala.collection.mutable.Set
import scala.collection.mutable.Map
import java.util.Date
import ar.edu.unq.obj3.O3_TP1.grupos.Agenda
import ar.edu.unq.obj3.O3_TP1.aula.Asignable

abstract class Actividad( val nombre : String,
		val costo : Int,
		val fechaPresentacion : Date,
		val responsable : Persona,
		val otros : Persona* ) {

	var fechaAprobacion : Date = null

	def integrantes = ( Set[Persona]( responsable ) ++= otros )

	def agregarEnAgenda( agenda : Agenda )

}
case class Seminario( override val nombre : String, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, val recursos : Set[String], override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* )
		with Asignable {

	val sesiones : Set[Sesion] = Set()

	def crearSesion( fecha : Date, horaInicio : Int, horaFin : Int ) {
		sesiones += new Sesion( fecha, horaInicio, horaFin, this )
	}

	override def agregarEnAgenda( agenda : Agenda ) = {
		this.sesiones foreach { sesion =>
			sesion.agregarEnAgenda( agenda )
		}
	}

}
case class Charla( override val nombre : String, val fecha : Date, val horaInicio : Int, val horaFin : Int, val publico : Int, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, val recursos : Set[String], override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* )
		with Asignable {
	override def agregarEnAgenda( agenda : Agenda ) = {
		agenda.agregarEntrada( this.fecha, this.horaInicio, this.horaFin, this.nombre )
	}

}
case class Proyecto( override val nombre : String, override val costo : Int, override val fechaPresentacion : Date, override val responsable : Persona, override val otros : Persona* )
		extends Actividad( nombre, costo, fechaPresentacion, responsable, otros : _* ) {

	val bitacora = Set[Experimento]()
	val resultados = Map[String, Date]()

	def articulos : Map[String, Date] = {
		resultados.filter( { case ( r, s ) => r contains "articulo" } )
	}

	def agregarResultado( descripcion : String, fecha : Date ) =
		resultados( descripcion ) = fecha

	def agregarExperimentoEnBitacora( fecha : Date, horaDesde : Int, horaFin : Int, descripcion : String ) : Experimento = {
		val experimento = new Experimento( fecha, horaDesde, horaFin, descripcion )
		bitacora += experimento
		return experimento
	}
	override def agregarEnAgenda( agenda : Agenda ) = {
		this.bitacora foreach { experimento =>
			experimento.agregarEnAgenda( agenda )
		}
	}
}