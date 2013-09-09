package ar.edu.unq.obj3.O3_TP1.materia

import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso
import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre._
import ar.edu.unq.obj3.O3_TP1.persona.Profesor

class Materia( val nombre : String ) {

	def cursos : Set[Curso] = Set()

	def crearCurso( cuatrimestre : Cuatrimestre, profesores : Profesor* ) : Curso = {
		val curso : Curso = new Curso( this, cuatrimestre, profesores : _* )
		cursos += curso
		return curso
	}
}