package ar.edu.unq.obj3.O3_TP1.materia

import java.util.Date

object CualCuatrimestre extends Enumeration {

	type CuatrimestreNombre = Value
	val PRIMERO, SEGUNDO = Value

}

case class Cuatrimestre( val fechaInicio : Date, val fechaFin : Date ) {

}