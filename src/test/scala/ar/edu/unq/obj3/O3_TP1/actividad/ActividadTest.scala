package ar.edu.unq.obj3.O3_TP1.actividad

import java.util.Date

import scala.collection.mutable.Map
import scala.collection.mutable.Set

import org.scalatest.BeforeAndAfterEach
import org.scalatest.Finders
import org.scalatest.FlatSpec

import ar.edu.unq.obj3.O3_TP1.grupos.Agenda
import ar.edu.unq.obj3.O3_TP1.persona.Persona
import ar.edu.unq.obj3.O3_TP1.utils.DateSugar
import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions

class ActividadTest extends FlatSpec with BeforeAndAfterEach with MockitoSugarExtentions with DateSugar {

	var proyecto : Proyecto = _
	var responsable, p1, p2, p3 : Persona = _
	var experimentos : Set[Experimento] = _
	val fecha1 = "10/10/2013".toDate()
	val fecha2 = "10/11/2013".toDate()
	val fecha3 = "10/12/2013".toDate()

	override def beforeEach() {
		proyecto = Proyecto( "proyecto", 100, "20/05/2013".toDate(), responsable, p1, p2, p3 )
		experimentos = Set[Experimento](
			proyecto.agregarExperimentoEnBitacora( fecha1, 10, 11, "experimento de prueba" ),
			proyecto.agregarExperimentoEnBitacora( fecha2, 10, 11, "experimento de prueba" ),
			proyecto.agregarExperimentoEnBitacora( fecha3, 10, 11, "experimento de prueba" ) )
	}

	"Proyecto" should "agregar experimento en bitacora" in {

		assert( proyecto.bitacora.size === 3 )
		assert( experimentos === proyecto.bitacora )
	}

	it should "agregar en agenda" in {
		val agenda = <--[Agenda]
		proyecto.agregarEnAgenda( agenda )
		agenda.verify().agregarEntrada( fecha1, 10, 11, "experimento de prueba" )
		agenda.verify().agregarEntrada( fecha2, 10, 11, "experimento de prueba" )
		agenda.verify().agregarEntrada( fecha3, 10, 11, "experimento de prueba" )
	}

	it should "dar los articulos" in {
		proyecto.agregarResultado( "como hacer un articulo", fecha1 )
		proyecto.agregarResultado( "como hacer un articulo2", fecha2 )
		proyecto.agregarResultado( "como hacer un ensayo", fecha3 )
		assert( proyecto.articulos === Map[String, Date](
			"como hacer un articulo" -> fecha1,
			"como hacer un articulo2" -> fecha2 ) )
	}
}

class SeminarioTest extends FlatSpec with BeforeAndAfterEach with MockitoSugarExtentions with DateSugar {
	var proyecto : Proyecto = _
	var responsable, p1, p2, p3 : Persona = _
	var seminario : Seminario = _
	val fecha1 = "10/10/2013".toDate()
	val fecha2 = "10/11/2013".toDate()
	val fecha3 = "10/12/2013".toDate()

	override def beforeEach() {
		val recursos = Set[String]()
		seminario = Seminario( "Seminario", 100, fecha1, responsable, recursos, p1, p2, p3 )
	}

	"Seminario" should "agregar en agenda" in {
		seminario.crearSesion( fecha1, 10, 11 )
		seminario.crearSesion( fecha2, 10, 11 )
		seminario.crearSesion( fecha3, 10, 11 )
		val agenda = <--[Agenda]
		seminario.agregarEnAgenda( agenda )
		agenda.verify().agregarEntrada( fecha1, 10, 11, "Seminario" )
		agenda.verify().agregarEntrada( fecha2, 10, 11, "Seminario" )
		agenda.verify().agregarEntrada( fecha3, 10, 11, "Seminario" )
	}
}