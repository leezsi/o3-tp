package ar.edu.unq.obj3.O3_TP1.actividad

import java.util.Date

class Sesion( val fecha : Date, val horaInicio : Int, val horaFin : Int, val seminario : Seminario ) {

	def descripcion = seminario.nombre

	def agregarEnAgenda( agenda : Agenda ) : Agenda = {
		agenda.agregarEntrada( this.fecha, this.horaInicio, this.horaFin, this.descripcion )
	}
}