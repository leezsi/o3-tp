package ar.edu.unq.obj3.O3_TP1.aula

import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre

trait Asignable {

	def meSirve( aula : Aula ) : Boolean = {
		aula.tieneRecursos( this.recursos ) &&
			aula.capacidad >= asistentes
	}

	def recursos : Set[String]
	def asistentes : Int = 0
}