package ar.edu.unq.obj3.O3_TP1.persona

import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.carrera.Carrera
import ar.edu.unq.obj3.O3_TP1.grupos.GrupoDeInvestigacion
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursable

abstract class Persona( nombre : String ) extends Cursable {
	val grupos = Set[GrupoDeInvestigacion]()

	def agregarEnGrupo( grupo : GrupoDeInvestigacion ) {
		grupos += grupo
	}
}

case class Profesor( nombre : String ) extends Persona( nombre ) {

}

case class Estudiante( nombre : String, carrera : Carrera ) extends Persona( nombre ) {

	def anotarEn( curso : Curso ) : Cursada = {
		val cursada = Cursada( this, curso )
		cursadas += cursada
		carrera.cursadas += cursada
		return cursada
	}

	def definirNota( curso : Curso, nota : Option[Float] ) =
		cursadas.find( _.curso == curso ).get.terminar( nota )

}
