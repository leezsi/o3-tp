package ar.edu.unq.obj3.O3_TP1.materia.curso

import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado._
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import com.sun.xml.internal.bind.v2.model.core.MaybeElement
import ar.edu.unq.obj3.O3_TP1.persona.Estudiante

class Cursada( val estudiante : Estudiante, val curso : Curso ) {

	def nota() : Option[Int] = None
	def estado() : Estado = Estado.ABANDONO

	def terminar( nota : Option[Int] ) {
			def estado() = calcularEstado( nota )
	}

	def calcularEstado( n : Option[Int] ) : Estado = n match {
		case None => Estado.ABANDONO
		case Some( n1 ) if 0 == n1 | n1 <= 3 ⇒ Estado.DESAPROBADO
		case _ ⇒ Estado.APROBADO
	}
}