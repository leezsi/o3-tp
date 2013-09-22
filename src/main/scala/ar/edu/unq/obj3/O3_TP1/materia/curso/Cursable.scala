package ar.edu.unq.obj3.O3_TP1.materia.curso

import scala.collection.mutable.Set
import scala.collection.mutable.Map

trait Cursable {

	def cursadas = Set[Cursada]()

	def promedioSinAplazos() : Float = {
		val notas = cursadas collect { case c : Cursada if c.aprobada => c.nota.get }
		notas.sum / notas.size
	}

	def promedioConAplazos() : Float = {
		val notas = cursadas collect { case c : Cursada if !c.abandonada => c.nota.get }
		notas.sum / notas.size
	}

	def cantidadDeAprobadas() : Int = {
		( Cursable.this.cursadas filter { ( c : Cursada ) => c.aprobada } ).size
	}

	def cantidadDeAbandonadas() : Int = {
		( Cursable.this.cursadas filter { ( c : Cursada ) => c.abandonada } ).size
	}

	def aprobadosSobreIniciados() : Float = {
		( cantidadDeAprobadas() * 100 ) / cursadas.size
	}

	private def cuantos( nota : Float ) : Int = {
		( cursadas collect { case c : Cursada if c.nota == Some( nota ) => 1 } ).size
	}
	def notas() : Map[Int, Int] = {
		val ret = Map[Int, Int]()
		( 1 to 10 ) foreach {
			index => ret( index ) = cuantos( index )
		}
		return ret
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

	def alcazadoN( n : Int ) : Option[Float] = {
		val notas = Map[Int, Int]( ( 1 -> 0 ), ( 2 -> 0 ), ( 3 -> 0 ), ( 4 -> 0 ), ( 5 -> 0 ), ( 6 -> 0 ), ( 7 -> 0 ), ( 8 -> 0 ), ( 9 -> 0 ), ( 10 -> 0 ) )
		var cantidad = 0
		1 to 10 foreach {
			nota => sumarNHastaJ( notas, cuantos( nota ), nota )
		}
		alcanzadoNVeces( notas, n )
	}

}