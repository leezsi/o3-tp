package ar.edu.unq.obj3.O3_TP1.utils

import java.util.Date
import java.text.SimpleDateFormat

trait DateSugar {

	case class ToDate( source : String ) {

		def toDate( implicit formatSource : String ) : Date = {
			val format = new SimpleDateFormat( formatSource )
			format.parse( source )
		}
		def toDate() : Date = {
			toDate( "dd/MM/yyyy" )
		}
	}

	implicit def convertToDateWrapper( source : String ) : ToDate = ToDate( source )

}