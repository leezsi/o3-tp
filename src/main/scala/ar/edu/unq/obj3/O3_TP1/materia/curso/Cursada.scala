package ar.edu.unq.obj3.O3_TP1.materia.curso

import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado._
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import com.sun.xml.internal.bind.v2.model.core.MaybeElement
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante

case class Cursada( val estudiante : Estudiante, val curso : Curso ) {

	var nota : Option[Float] = None
	var estado : Estado = Estado.ABANDONO

	def terminar( nota : Option[Float] ) {
		estado = nota match {
			case None => Estado.ABANDONO
			case Some( n1 ) if n1 <= 3 ⇒ Estado.DESAPROBADO
			case _ => Estado.APROBADO
		}
	}

	def abandonada = this.estado match {
		case Estado.ABANDONO => true
		case _ => false
	}
	def aprobada = this.estado match {
		case Estado.APROBADO => true
		case _ => false
	}
	def aplazada = this.estado match {
		case Estado.DESAPROBADO => true
		case _ => false
	}
}