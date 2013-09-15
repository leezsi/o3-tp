package ar.edu.unq.obj3.O3_TP1.utils

import org.scalatest.mock.MockitoSugar
import org.mockito.stubbing.OngoingStubbing
import org.mockito.Mockito
import org.mockito.Mockito.{ mock => mockitoMock }
import scala.collection.mutable.Map

trait MockitoSugarExtentions {

	case class OngoingStubbingWrapper[L]( left : L ) {

		def -->( implicit ret : L ) : OngoingStubbing[L] = {
			Mockito.when( left ).thenReturn( ret )

		}

		def *() : OngoingStubbing[L] = {
			Mockito.when( left ).thenCallRealMethod()
		}

	}

	implicit def convertToOngoingStubbingWrapper[L]( any : L ) : OngoingStubbingWrapper[L] = OngoingStubbingWrapper( any )

	//assign return method for an specific one
	implicit def -->[L]( ret : L )( implicit wr : OngoingStubbingWrapper[L] ) : OngoingStubbing[L] = {
		wr --> ret
	}

	//Unstub a method
	implicit def *[L]( implicit wr : OngoingStubbingWrapper[L] ) : OngoingStubbing[L] = {
		wr.*
	}

	def <--[L <: AnyRef]( implicit manifest : Manifest[L] ) : L = {
		mockitoMock( manifest.erasure.asInstanceOf[Class[L]] )
	}

}
