package ar.edu.unq.obj3.O3_TP1.materia.curso

import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado._
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import com.sun.xml.internal.bind.v2.model.core.MaybeElement
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante

case class Cursada( val estudiante : Estudiante, val curso : Curso ) {

	var nota : Option[Int] = None
	var estado : Estado = Estado.ABANDONO

	def terminar( nota : Option[Int] ) {
		estado = calcularEstado( nota )
	}

	def calcularEstado( n : Option[Int] ) : Estado = n match {
		case None => Estado.ABANDONO
		case Some( n1 ) if n1 <= 3 ⇒ Estado.DESAPROBADO
		case _ => Estado.APROBADO
	}
}