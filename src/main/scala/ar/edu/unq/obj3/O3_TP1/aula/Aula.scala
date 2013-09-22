package ar.edu.unq.obj3.O3_TP1.aula

import scala.collection.mutable.Set
import java.util.Date

case class Rango( val fechaInicio : Date, val fechaFin : Date, val dia : Date, val horaInicio : Int, val horaFin : Int ) {

}

case class Aula( val capacidad : Int, val metros2Pizarron : Int, val recursos : Set[String] ) {

	val asignados = Set[Rango]()

	def agregarRecurso( recurso : String* ) {
		recursos.++=( recursos )
	}

	def tieneRecursos( solicitados : Set[String] ) : Boolean = solicitados.subsetOf( recursos )

	def estaDisponible( fecha : Date, horaInicio : Int, horaFin : Int ) : Boolean = {
		true;
	}
}