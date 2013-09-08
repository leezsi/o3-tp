package ar.edu.unq.obj3.O3_TP1.materia.estadisticas

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante

object Estadisticas {

	def cantidadDeAprobadas( estudiante : Estudiante ) : Int = {
		( filtrar( estudiante ) { c => c.estado.equals( Estado.APROBADO ) } ).size
	}

	def cantidadDeAbandonos( estudiante : Estudiante ) : Int = {
		( filtrar( estudiante ) { c => c.estado.equals( Estado.ABANDONO ) } ).size
	}

	def filtrar( estudiante : Estudiante )( condicion : Cursada => Boolean ) : Set[Cursada] = {
		estudiante.cursadas filter condicion
	}

	def sumatoria( cursadas : Set[Cursada] ) : Int = {
		cursadas.foldRight( 0 )( ( ( c, a ) => {
			c.nota match {
				case None => a
				case Some( n ) => a + n
			}
		} ) )
	}
	def aprobadas( estudiante : Estudiante ) : Set[Cursada] = {
		filtrar( estudiante ) { c => c.estado.equals( Estado.APROBADO ) }
	}

	def conAplazos( estudiante : Estudiante ) : Set[Cursada] = {
		filtrar( estudiante ) { c => !c.estado.equals( Estado.ABANDONO ) }
	}

	def promedioSinAplazos( estudiante : Estudiante ) : Float = {
		val _aprobadas : Set[Cursada] = aprobadas( estudiante )
		sumatoria( _aprobadas ) / _aprobadas.size
	}
	def promedioConAplazos( estudiante : Estudiante ) : Float = {
		val cursadas : Set[Cursada] = conAplazos( estudiante )
		sumatoria( cursadas ) / cursadas.size
	}

	def aprobadosSobreIniciados( estudiante : Estudiante ) : Float = {
		cantidadDeAprobadas( estudiante ) * 100 / estudiante.cursadas.size
	}

	def cuantos( estudiante : Estudiante, nota : Int ) : Int = {
		val enNota = filtrar( estudiante )( ( c => {
			c.nota match {
				case None => false
				case Some( n ) => n == nota
			}
		} ) )
		enNota.foldRight( 0 ) { ( c, ac ) => ac + 1 }
	}

	def notas( estudiante : Estudiante ) : Map[Int, Int] = {
		val ret : Map[Int, Int] = Map()
		Range( 1, 10 ) foreach {
			index =>
				ret.+=( ( index, cuantos( estudiante, index ) ) )
		}
		return ret
	}

}