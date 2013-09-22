package ar.edu.unq.obj3.O3_TP1.persona

import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursable
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada
import scala.collection.mutable.Set

case class CorteAnual( val persona : Persona, val anio : Int ) extends Cursable {

	override def cursadas = persona.cursadas.filter { c : Cursada => c.curso.esDelAnio( anio ) }
}
