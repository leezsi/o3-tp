package ar.edu.unq.obj3.O3_TP1.grupos

import ar.edu.unq.obj3.O3_TP1.persona.Persona
import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.actividad.Actividad
import ar.edu.unq.obj3.O3_TP1.actividad.Seminario
import java.util.Date
import ar.edu.unq.obj3.O3_TP1.actividad.Charla

class GrupoDeInvestigacion( val integrantes : Persona* ) {

	val actividades : Set[Actividad] = Set()

	integrantes.foreach( ( p ) => p.agregarEnGrupo( this ) )

	def crearSeminario( nombre : String, costo : Int, fechaPresentacion : Date, responsable : Persona, integrantes : Persona* ) {
		actividades += ( Seminario( nombre, costo, fechaPresentacion, responsable, integrantes : _* ) )
	}

	def crearCharla( nombre : String, fecha : Date, horaInicio : Int, horaFin : Int, publico : Int, costo : Int, fechaPresentacion : Date, responsable : Persona, otros : Persona* ) {
		actividades += ( Charla( nombre, fecha, horaInicio, horaFin, publico, costo, fechaPresentacion, responsable, integrantes : _* ) )
	}

}