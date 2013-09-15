package ar.edu.unq.obj3.O3_TP1.utils

import org.scalatest.mock.MockitoSugar
import org.mockito.stubbing.OngoingStubbing
import org.mockito.Mockito
import org.mockito.Mockito.{ mock => mockitoMock }

trait MockitoSugarExtentions { //}extends MockitoSugar {

	case class OngoingStubbingWrapper[L]( left : L ) {

		def -->( implicit ret : L ) : OngoingStubbing[L] = {
			Mockito.when( left ).thenReturn( ret )

		}

		def real() : OngoingStubbing[L] = {
			Mockito.when( left ).thenCallRealMethod()
		}

	}

	implicit def convertToOngoingStubbingWrapper[L]( any : L ) : OngoingStubbingWrapper[L] = OngoingStubbingWrapper( any )

	implicit def -->[L]( ret : L )( implicit wr : OngoingStubbingWrapper[L] ) : OngoingStubbing[L] = {
		wr --> ret
	}

	implicit def real[L]( implicit wr : OngoingStubbingWrapper[L] ) : OngoingStubbing[L] = {
		wr.real
	}

	def <--[L <: AnyRef]( implicit manifest : Manifest[L] ) : L = {
		mockitoMock( manifest.erasure.asInstanceOf[Class[L]] )
	}

}

