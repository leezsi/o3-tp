package ar.edu.unq.obj3.O3_TP1.grupos

import ar.edu.unq.obj3.O3_TP1.persona.Persona
import scala.collection.mutable.Set
import ar.edu.unq.obj3.O3_TP1.actividad.Actividad
import ar.edu.unq.obj3.O3_TP1.actividad.Seminario
import java.util.Date

class GrupoDeInvestigacion( val integrantes : Persona* ) {

	val actividades : Set[Actividad] = Set()

	integrantes.foreach( ( p ) => p.agregarEnGrupo( this ) )

	def crearSeminario( nombre : String, costo : Int, fechaPresentacion : Date, responsable : Persona, integrantes : Persona* ) {
		actividades += ( new Seminario( nombre, costo, fechaPresentacion, responsable, integrantes : _* ) )
	}

}