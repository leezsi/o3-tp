package ar.edu.unq.obj3.O3_TP1.actividad

import java.util.Date
import ar.edu.unq.obj3.O3_TP1.grupos.Agenda

class Experimento( val fecha : Date, val horaDesde : Int, val horaFin : Int, val descripcion : String ) {
	def agregarEnAgenda( agenda : Agenda ) : Agenda = {
		agenda.agregarEntrada( this.fecha, this.horaDesde, this.horaFin, this.descripcion )
	}
}