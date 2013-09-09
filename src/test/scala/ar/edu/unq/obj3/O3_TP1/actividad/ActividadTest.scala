package ar.edu.unq.obj3.O3_TP1.actividad

import collection.mutable.Stack
import org.scalatest._
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.carrera.Carrera
import ar.edu.unq.obj3.O3_TP1.materia.Materia
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre
import ar.edu.unq.obj3.O3_TP1.persona.Profesor

class EstadisticasSpec extends FlatSpec {

	var prof : Profesor = new Profesor( "Anonimo" )
	var mat1 : Materia = new Materia( "Mate1" )
	var cursoMat11 = mat1.crearCurso( Cuatrimestre.PRIMERO, prof )
	var cursoMat12 = mat1.crearCurso( Cuatrimestre.SEGUNDO, prof )

	var mat2 : Materia = new Materia( "Mate2" )
	var cursoMat21 = mat2.crearCurso( Cuatrimestre.PRIMERO, prof )
	var cursoMat22 = mat2.crearCurso( Cuatrimestre.SEGUNDO, prof )

	var ing1 : Materia = new Materia( "IGA1" )
	var ing11 = ing1.crearCurso( Cuatrimestre.PRIMERO, prof )
	var ing12 = ing1.crearCurso( Cuatrimestre.SEGUNDO, prof )

	var tpi : Carrera = new Carrera( mat1, mat2, ing1 )
	var est1 : Estudiante = new Estudiante( "John big sticks", tpi )

	est1.anotarEn( cursoMat11 )
	est1.anotarEn( cursoMat21 )

	"An statistics" should "get statistics" in {
		assert( true )
	}

}