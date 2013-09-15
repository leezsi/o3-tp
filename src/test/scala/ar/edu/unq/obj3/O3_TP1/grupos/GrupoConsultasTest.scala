package ar.edu.unq.obj3.O3_TP1.grupos

import java.util.Date
import scala.collection.mutable.Map
import scala.collection.mutable.Seq
import scala.collection.mutable.Set
import org.scalatest.BeforeAndAfterEach
import org.scalatest.Finders
import org.scalatest.FlatSpec
import ar.edu.unq.obj3.O3_TP1.actividad.Actividad
import ar.edu.unq.obj3.O3_TP1.actividad.Charla
import ar.edu.unq.obj3.O3_TP1.actividad.Proyecto
import ar.edu.unq.obj3.O3_TP1.actividad.Seminario
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante
import ar.edu.unq.obj3.O3_TP1.persona.Persona
import ar.edu.unq.obj3.O3_TP1.persona.Profesor
import ar.edu.unq.obj3.O3_TP1.utils.DateSugar
import ar.edu.unq.obj3.O3_TP1.utils.MockitoSugarExtentions
import ar.edu.unq.obj3.O3_TP1.actividad.Sesion

class GrupoConsultasTest extends FlatSpec with BeforeAndAfterEach with MockitoSugarExtentions with DateSugar {

	def mockAlumno( nombre : String ) : Estudiante = {
		var alumno = <--[Estudiante]
		alumno.nombre --> nombre
		return alumno
	}

	def mockProfesor( nombre : String ) : Profesor = {
		var profesor = <--[Profesor]
		profesor.nombre --> nombre
		return profesor
	}

	def mockSeminario( nombre : String, costo : Int, sesiones : Set[Sesion], responsable : Persona, otros : Persona* ) : Seminario = {
		val seminario : Seminario = <--[Seminario]
		seminario.nombre --> nombre
		seminario.costo --> costo
		seminario.sesiones --> sesiones
		seminario.responsable --> responsable
		seminario.integrantes --> ( Set[Persona]( responsable ) ++= otros )
		return seminario
	}

	def mockCharla( nombre : String, costo : Int ) : Charla = {
		val charla = <--[Charla]
		charla.nombre --> nombre
		charla.costo --> costo
		return charla
	}

	def mockProyecto( nombre : String, costo : Int ) : Proyecto = {
		val proyecto = <--[Proyecto]
		proyecto.nombre --> nombre
		proyecto.costo --> costo
		return proyecto
	}

	def mockSesion( fecha : String, hi : Int, hf : Int ) : Sesion = {
		val sesion = <--[Sesion]
		sesion.fecha --> fecha.toDate()
		sesion.horaInicio --> hi
		sesion.horaFin --> hf
		return sesion
	}

	var grupo = <--[GrupoDeInvestigacion]
	val p1 = mockAlumno( "Leandro" )
	val p2 = mockAlumno( "Gustavo" )
	val p3 = mockProfesor( "Nico" )
	val p4 = mockProfesor( "Charly" )
	val ss1 = mockSeminario( "Sem1", 100, Set[Sesion](
		mockSesion( "10/10/2031", 10, 12 ),
		mockSesion( "11/10/2031", 10, 12 ),
		mockSesion( "12/10/2031", 10, 12 ) ), p1, p2, p3 )

	val ss2 = mockSeminario( "Sem2", 150, Set[Sesion](
		mockSesion( "13/10/2031", 10, 12 ),
		mockSesion( "14/10/2031", 10, 12 ),
		mockSesion( "15/10/2031", 10, 12 ) ), p2, p4 )
	val sa1 = mockSeminario( "Sem2", 10, Set[Sesion](
		mockSesion( "16/10/2031", 10, 12 ),
		mockSesion( "17/10/2031", 10, 12 ),
		mockSesion( "18/10/2031", 10, 12 ) ), p3, p2, p4 )

	p1.grupos --> Set[GrupoDeInvestigacion]( grupo )
	p2.grupos --> Set[GrupoDeInvestigacion]( grupo )
	p3.grupos --> Set[GrupoDeInvestigacion]( grupo )
	p4.grupos --> Set[GrupoDeInvestigacion]( grupo )

	sa1.fechaAprobacion --> "25/11/20013".toDate()

	val cs1 = mockCharla( "cha1", 100 )
	val cs2 = mockCharla( "cha2", 1000 )
	val ca1 = mockCharla( "cha3", 10 )
	ca1.fechaAprobacion --> "25/11/20013".toDate()

	val pr1 = mockProyecto( "pro1", 200 )
	pr1.articulos --> Map[String, Date]( "proyecto de articulo 1" -> "10/09/2013".toDate(), "proyecto de articulo 2" -> "10/09/2013".toDate() )

	val pr2 = mockProyecto( "pro2", 20 )
	pr2.articulos --> Map[String, Date]( "proyecto de articulo 3" -> "10/09/2013".toDate() )

	val actividades = Set[Actividad]( ss1, cs1, cs2, ca1, sa1, pr1, pr2 )

	grupo.integrantes --> Seq[Persona]( p1, p2, p3, p4 )
	grupo.actividades --> actividades

	"GrupoConsultas" should "presentadas sin aprobacion" in {
		val sinAprobar = Set[Actividad]( ss1, cs1, cs2, pr1, pr2 )
		assert( GrupoConsultas.sinAprobar( grupo ) === sinAprobar )
	}
	it should "costo total" in {
		assert( GrupoConsultas.costoTotal( grupo ) === 1440 )
	}

	it should "dar los articulos" in {
		val r = Map[String, Date](
			"proyecto de articulo 1" -> "10/09/2013".toDate(),
			"proyecto de articulo 2" -> "10/09/2013".toDate(),
			"proyecto de articulo 3" -> "10/09/2013".toDate() )
		assert( GrupoConsultas.articulos( grupo ) === r )
	}
	it should "dar la agenda" in {
		assert( GrupoConsultas.agenda( p1 ) === 1 )
	}
}