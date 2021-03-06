package ar.edu.unq.obj3.O3_TP1.utils

import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado
import ar.edu.unq.obj3.O3_TP1.materia.estado.Estado._
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursada

trait CursadaMocks extends MockitoSugarExtentions {

	val cursada1Aprobada = mockCursada( 7, Estado.APROBADO )

	val cursada2Aprobada = mockCursada( 8, Estado.APROBADO )

	val cursada3Aprobada = mockCursada( 9, Estado.APROBADO )

	val cursada4Aprobada = mockCursada( 10, Estado.APROBADO )

	val cursada5Aprobada = mockCursada( 5, Estado.APROBADO )

	val cursada1Desaprobada = mockCursada( 1, Estado.DESAPROBADO )

	val cursada2Desaprobada = mockCursada( 3, Estado.DESAPROBADO )

	val cursada3Desaprobada = mockCursada( 2, Estado.DESAPROBADO )

	val cursada4Desaprobada = mockCursada( 2, Estado.DESAPROBADO )

	val cursada5Desaprobada = mockCursada( 1, Estado.DESAPROBADO )

	val cursada1Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada2Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada3Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada4Abandonada = mockCursada( 0, Estado.ABANDONO )

	val cursada5Abandonada = mockCursada( 0, Estado.ABANDONO )
	def mockCursada( nota : Int, estado : Estado ) : Cursada = {
		val cursada = <--[Cursada]
		cursada.nota --> Some( nota )
		cursada.estado --> estado
		estado match {
			case Estado.APROBADO => {
				cursada.aprobada --> true
				cursada.aplazada --> false
				cursada.abandonada --> false
			}
			case Estado.DESAPROBADO => {
				cursada.aprobada --> false
				cursada.aplazada --> true
				cursada.abandonada --> false
			}
			case Estado.ABANDONO => {
				cursada.aprobada --> false
				cursada.aplazada --> false
				cursada.abandonada --> true
			}
		}
		cursada.toString --> ( "nota: "+nota+" estado: "+estado )
		return cursada
	}
}