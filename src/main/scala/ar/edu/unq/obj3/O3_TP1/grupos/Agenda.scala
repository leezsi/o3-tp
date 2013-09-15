package ar.edu.unq.obj3.O3_TP1.grupos

import java.util.Date
import scala.collection.mutable.Set

case class Agenda() {

	class AgendaEntry( val fecha : Date, val horaInicio : Int, val horaFin : Int, val descripcion : String )

	def entries : Set[AgendaEntry] = Set()

	def agregarEntrada( fecha : Date, horaInicio : Int, horaFin : Int, descripcion : String ) : Agenda = {
		entries += new AgendaEntry( fecha, horaInicio, horaFin, descripcion )
		return this;
	}
}