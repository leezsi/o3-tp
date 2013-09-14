package ar.edu.unq.obj3.O3_TP1.persona

import scala.collection.mutable.Set

import ar.edu.unq.obj3.O3_TP1.carrera.Carrera
import ar.edu.unq.obj3.O3_TP1.grupos.GrupoDeInvestigacion
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso

abstract class Persona( nombre : String ) {
	val grupos = Set[GrupoDeInvestigacion]()

	def agregarEnGrupo( grupo : GrupoDeInvestigacion ) {
		grupos += grupo
	}
}

case class Profesor( nombre : String ) extends Persona( nombre ) {

}

case class Estudiante( nombre : String, carrera : Carrera ) extends Persona( nombre ) {
	val cursadas = Set[Cursada]()

	def anotarEn( curso : Curso ) = cursadas += Cursada( this, curso )

	def definirNota( curso : Curso, nota : Option[Float] ) =
		cursadas.find( _.curso == curso ).get.terminar( nota )
}
