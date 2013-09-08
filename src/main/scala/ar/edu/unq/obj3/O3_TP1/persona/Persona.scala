package ar.edu.unq.obj3.O3_TP1.persona

import scala.collection.mutable.Set

import ar.edu.unq.obj3.O3_TP1.carrera.Carrera
import ar.edu.unq.obj3.O3_TP1.grupos.GrupoDeInvestigacion
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso

abstract class Persona( val nombre : String ) {
	val grupos : Set[GrupoDeInvestigacion] = Set()

	def agregarEnGrupo( grupo : GrupoDeInvestigacion ) {
		grupos += grupo
	}
}

case class Profesor( override val nombre : String ) extends Persona( nombre ) {

}

case class Estudiante( override val nombre : String, val carrera : Carrera ) extends Persona( nombre ) {

	var cursadas : Set[Cursada] = Set()

	def anotarEn( curso : Curso ) {
		cursadas.+=( Cursada( this, curso ) );
	}

	def definirNota( curso : Curso, nota : Option[Int] ) {

		val cursada : Cursada = ( cursadas collectFirst { case c : Cursada if c.curso.eq( curso ) => c } ).get
		//		val cursada : Cursada = this.cursadas.filter( _.curso.equals( curso ) ).head
		cursada.terminar( nota );
	}

}
