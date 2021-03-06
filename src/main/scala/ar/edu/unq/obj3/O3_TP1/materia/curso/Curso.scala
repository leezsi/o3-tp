package ar.edu.unq.obj3.O3_TP1.materia.curso

import ar.edu.unq.obj3.O3_TP1.materia.Materia
import ar.edu.unq.obj3.O3_TP1.persona.Profesor
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre

case class Curso( val materia : Materia, val anio : Int, val dia : Int, val cuatrimestre : Cuatrimestre, val profesores : Profesor* ) extends Cursable {

	def agregarCursada( cursada : Cursada ) {
		this.cursadas += cursada
		profesores.foreach { profesor =>
			profesor.cursadas += cursada
		}
		materia.agregarCursada( cursada )
	}

	def esDelAnio( anio : Int ) : Boolean = {
		this.anio == anio
	}
}