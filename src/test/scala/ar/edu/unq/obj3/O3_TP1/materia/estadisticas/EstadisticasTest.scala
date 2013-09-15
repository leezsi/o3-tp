package ar.edu.unq.obj3.O3_TP1.materia.estadisticas

import scala.collection.mutable.Map
import scala.collection.mutable.Set
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado.Estado
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions
import ar.edu.unq.obj3.O3_TP1.utils.CursadaMocks

class ExampleSpec extends FlatSpec with BeforeAndAfterEach with CursadaMocks with MockitoSugarExtentions {

	var estudiante : Estudiante = _
	val cursadasPromedio = Set[Cursada](
		cursada1Abandonada,
		cursada1Aprobada,
		cursada2Aprobada,
		cursada3Aprobada,
		cursada1Desaprobada )
	override def beforeEach() {
		estudiante = <--[Estudiante]
	}

	"Estadisticas" should "dar promedios sin aplazos" in {

		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.promedioSinAplazos( estudiante ) === 8 )
	}
	it should "promedio con aplazos" in {

		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.promedioConAplazos( estudiante ) === 6.25 )
	}
	it should "dar cantidad de cursadas aprobadas" in {

		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.cantidadDeAprobadas( estudiante ) === 3 )
	}

	it should "dar cantidad de cursadas abandonadas" in {

		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.cantidadDeAbandonos( estudiante ) === 1 )
	}

	it should "dar porcentaje de cursos aprobados sobre cursos iniciados" in {
		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.aprobadosSobreIniciados( estudiante ) === 60 )
	}

	it should "dar tabla de notas" in {
		val notas = Map[Int, Int]( ( 1 -> 1 ), ( 2 -> 0 ), ( 3 -> 0 ), ( 4 -> 0 ), ( 5 -> 0 ), ( 6 -> 0 ), ( 7 -> 1 ), ( 8 -> 1 ), ( 9 -> 1 ), ( 10 -> 0 ) )
		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.notas( estudiante ) === notas )
	}

	it should "cual es la nota mas alta a la que llego" in {
		val notas = Map[Int, Int]( ( 1 -> 1 ), ( 2 -> 0 ), ( 3 -> 0 ), ( 4 -> 0 ), ( 5 -> 0 ), ( 6 -> 0 ), ( 7 -> 1 ), ( 8 -> 1 ), ( 9 -> 1 ), ( 10 -> 0 ) )
		estudiante.cursadas --> cursadasPromedio
		assert( Estadisticas.alcazadoN( estudiante, 3 ) === Some( 7 ) )

	}

}