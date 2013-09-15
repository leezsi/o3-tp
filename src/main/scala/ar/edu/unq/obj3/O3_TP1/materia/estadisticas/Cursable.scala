package ar.edu.unq.obj3.O3_TP1.materia.estadisticas

import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import scala.collection.mutable.Set

trait Cursable {

	var cursadas = Set[Cursada]()

	def cantidadDeAprobadas() : Int = {
		( Cursable.this.cursadas filter { ( c : Cursada ) => c.aprobada } ).size
	}

	def promedioSinAplazos() : Float = {
		val notas = cursadas collect { case c : Cursada if c.aprobada => c.nota.get }
		notas.sum / notas.size
	}

}