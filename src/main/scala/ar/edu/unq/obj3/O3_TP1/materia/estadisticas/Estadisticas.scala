package ar.edu.unq.obj3.O3_TP1.materia.estadisticas

import scala.collection.mutable.Set
import scala.collection.mutable.Map
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada

object Estadisticas {

	def cantidadDeAprobadas( estudiante : Estudiante ) : Int = {
		( estudiante.cursadas filter { ( c : Cursada ) => c.aprobada } ).size
	}

	def cantidadDeAbandonos( estudiante : Estudiante ) : Int = {
		( estudiante.cursadas filter { ( c : Cursada ) => c.abandonada } ).size
	}

	def promedioSinAplazos( estudiante : Estudiante ) : Float = {
		val notas = estudiante.cursadas collect { case c : Cursada if c.aprobada => c.nota.get }
		notas.sum / notas.size
	}

	def promedioConAplazos( estudiante : Estudiante ) : Float = {
		val notas = estudiante.cursadas collect { case c : Cursada if !c.abandonada => c.nota.get }
		notas.sum / notas.size
	}

	def aprobadosSobreIniciados( estudiante : Estudiante ) : Float = {
		( cantidadDeAprobadas( estudiante ) * 100 ) / estudiante.cursadas.size
	}

	private def cuantos( estudiante : Estudiante, nota : Float ) : Int = {
		( estudiante.cursadas collect { case c : Cursada if c.nota == Some( nota ) => 1 } ).size
	}

	private def sumarNHastaJ( notas : Map[Int, Int], n : Int, j : Int ) {
		1 to j foreach {
			i => notas( i ) = notas( i ) + n
		}
	}

	private def alcanzadoNVeces( notas : Map[Int, Int], n : Int ) : Option[Float] = {
		var maxima : Option[Float] = None
		1 to 10 foreach {
			i =>
				if ( notas( i ) >= n ) {
					maxima = Some( i )
				}
		}
		return maxima
	}

	def alcazadoN( estudiante : Estudiante, n : Int ) : Option[Float] = {
		val notas = Map[Int, Int]( ( 1 -> 0 ), ( 2 -> 0 ), ( 3 -> 0 ), ( 4 -> 0 ), ( 5 -> 0 ), ( 6 -> 0 ), ( 7 -> 0 ), ( 8 -> 0 ), ( 9 -> 0 ), ( 10 -> 0 ) )
		var cantidad = 0
		1 to 10 foreach {
			nota => sumarNHastaJ( notas, cuantos( estudiante, nota ), nota )
		}
		alcanzadoNVeces( notas, n )
	}
	def notas( estudiante : Estudiante ) : Map[Int, Int] = {
		val ret = Map[Int, Int]()
		( 1 to 10 ) foreach {
			index => ret( index ) = cuantos( estudiante, index )
		}
		return ret
	}

}