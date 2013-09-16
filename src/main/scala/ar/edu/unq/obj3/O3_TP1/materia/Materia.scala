package ar.edu.unq.obj3.O3_TP1.materia

import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso
import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre._
import ar.edu.unq.obj3.O3_TP1.persona.Profesor
import ar.edu.unq.obj3.O3_TP1.materia.estadisticas.Cursable
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada

class Materia( val nombre : String ) extends Cursable {

	def cursos : Set[Curso] = Set()

	def crearCurso( cuatrimestre : Cuatrimestre, profesores : Profesor* ) : Curso = {
		val curso : Curso = new Curso( this, cuatrimestre, profesores : _* )
		cursos += curso
		return curso
	}

	def agregarCursada( cursada : Cursada ) {
		this.cursadas += cursada
	}
}