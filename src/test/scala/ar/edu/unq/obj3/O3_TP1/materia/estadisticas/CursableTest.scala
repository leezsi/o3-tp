package ar.edu.unq.obj3.O3_TP1.materia.estadisticas

import scala.collection.mutable.Set
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado.Estado
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions
import ar.edu.unq.obj3.O3_TP1.utils.CursadaMocks
import ar.edu.unq.obj3.O3_TP1.materia.curso.Curso
import ar.edu.unq.obj3.O3_TP1.persona.Profesor
import ar.edu.unq.obj3.O3_TP1.materia.Materia

class CursableTest extends FlatSpec with BeforeAndAfterEach with CursadaMocks with MockitoSugarExtentions {
	val cursadasPromedio = Set[Cursada](
		cursada1Abandonada,
		cursada1Aprobada,
		cursada2Aprobada,
		cursada3Aprobada,
		cursada1Desaprobada )

	override def beforeEach() {

	}
	"Cursable trait" should "dar promedio sin aplazos de un estudiante" in {
		val estudiante = <--[Estudiante]
		estudiante.cursadas --> cursadasPromedio
		estudiante.promedioSinAplazos.*()
		assert( estudiante.promedioSinAplazos === 8 )
	}

	it should "dar promedio sin aplazos de un curso" in {
		val curso = <--[Curso]
		curso.cursadas --> cursadasPromedio
		curso.promedioSinAplazos.*()
		assert( curso.promedioSinAplazos === 8 )
	}

	it should "dar promedio sin aplazos de un profesor" in {
		val profesor = <--[Profesor]
		profesor.cursadas --> cursadasPromedio
		profesor.promedioSinAplazos.*()
		assert( profesor.promedioSinAplazos === 8 )
	}
	it should "dar promedio sin aplazos de una materia" in {
		val materia = <--[Materia]
		materia.cursadas --> cursadasPromedio
		materia.promedioSinAplazos.*()
		assert( materia.promedioSinAplazos === 8 )
	}
}