package ar.edu.unq.obj3.O3_TP1.actividad

import scala.collection.mutable.Set
import scala.collection.mutable.Map

import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec

import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.estadisticas.Estadisticas
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado.Estado
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions

class ExampleSpec extends FlatSpec with BeforeAndAfterEach with MockitoSugarExtentions {

	var estudiante : Estudiante = _

	val cursada1Aprobada = mockCursada( 7, Estado.APROBADO )

	val cursada2Aprobada = mockCursada( 8, Estado.APROBADO )

	val cursada3Aprobada = mockCursada( 9, Estado.APROBADO )

	val cursada4Aprobada = mockCursada( 10, Estado.APROBADO )

	val cursada5Aprobada = mockCursada( 5, Estado.APROBADO )

	val cursada1Desaprobada = mockCursada( 1, Estado.DESAPROBADO )

	val cursada2Desaprobada = mockCursada( 3, Estado.DESAPROBADO )

	val cursada3Desaprobada = mockCursada( 2, Estado.DESAPROBADO )

	val cursada4Desaprobada = mockCursada( 2, Estado.DESAPROBADO )

	val cursada5Desaprobada = mockCursada( 1, Estado.DESAPROBADO )

	val cursada1Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada2Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada3Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada4Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada5Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursadasPromedio = Set[Cursada](
		cursada1Abandonada,
		cursada1Aprobada,
		cursada2Aprobada,
		cursada3Aprobada,
		cursada1Desaprobada )

	def mockCursada( nota : Int, estado : Estado ) : Cursada = {
		val cursada = mock[Cursada]
		cursada.nota --> Some( nota )
		cursada.estado --> estado
		estado match {
			case Estado.APROBADO => {
				cursada.aprobada --> true
				cursada.aplazada --> false
				cursada.abandonada --> false
			}
			case Estado.DESAPROBADO => {
				cursada.aprobada --> false
				cursada.aplazada --> true
				cursada.abandonada --> false
			}
			case Estado.ABANDONO => {
				cursada.aprobada --> false
				cursada.aplazada --> false
				cursada.abandonada --> true
			}
		}
		cursada.toString --> ( "nota: "+nota+" estado: "+estado )
		return cursada
	}

	override def beforeEach() {
		estudiante = mock[Estudiante]
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