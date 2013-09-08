package ar.edu.unq.obj3.O3_TP1.materia.curso

import ar.edu.unq.obj3.O3_TP1.materia.Materia
import ar.edu.unq.obj3.O3_TP1.persona.Profesor
import ar.edu.unq.obj3.O3_TP1.materia.Cuatrimestre._

class Curso( val materia : Materia, val cuatrimestre : Cuatrimestre, val profesores : Profesor* ) {
}