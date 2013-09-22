package ar.edu.unq.obj3.O3_TP1.carrera

import ar.edu.unq.obj3.O3_TP1.materia.Materia
import ar.edu.unq.obj3.O3_TP1.materia.curso.Cursable

case class Carrera( val materias : Materia* ) extends Cursable {

}