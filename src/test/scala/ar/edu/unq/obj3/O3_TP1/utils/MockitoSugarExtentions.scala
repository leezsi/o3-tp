package ar.edu.unq.obj3.O3_TP1.utils

import org.scalatest.mock.MockitoSugar
import org.mockito.stubbing.OngoingStubbing
import org.mockito.Mockito

trait MockitoSugarExtentions extends MockitoSugar {

	case class WhenReturn[L]( left : L ) {

		def -->( implicit ret : L ) : OngoingStubbing[L] = {
			Mockito.when( left ).thenReturn( ret )
		}

	}

	implicit def convertToWhenReturnWrapper[L]( any : L ) : WhenReturn[L] = WhenReturn( any )

	implicit def -->[L]( ret : L )( implicit wr : WhenReturn[L] ) : OngoingStubbing[L] = {
		wr --> ret
	}
}

