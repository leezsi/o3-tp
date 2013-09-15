package ar.edu.unq.obj3.O3_TP1.actividad

import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions
import org.scalatest.BeforeAndAfterEach
import org.scalatest.FlatSpec
import ar.edu.unq.obj3.O3_TP1.utils.DateSugar
import ar.edu.unq.obj3.O3_TP1.persona.Persona

class ProyectoTest extends FlatSpec with BeforeAndAfterEach with MockitoSugarExtentions with DateSugar {

	var proyecto : Proyecto = _
	var responsable, p1, p2, p3 : Persona = _

	override def beforeEach() {
		proyecto = Proyecto( "proyecto", 100, "20/05/2013".toDate( "dd/MM/yyyy" ), responsable, p1, p2, p3 )
	}

	"Proyecto" should "agregar experimento en bitacora" in {
		proyecto
	}
}