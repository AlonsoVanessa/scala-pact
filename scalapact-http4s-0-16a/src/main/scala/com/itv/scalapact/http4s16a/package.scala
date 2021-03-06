package com.itv.scalapact

import com.itv.scalapact.http4s16a.impl.{Http4sClientHelper, PactServer, ResultPublisher, ScalaPactHttpClient}
import com.itv.scalapact.shared.IResultPublisher
import com.itv.scalapact.shared.typeclasses.{IPactStubber, IScalaPactHttpClient}
import scalaz.concurrent.Task

package object http4s16a {

  // Note that we create a new stubber anytime this implicit is needed (i.e. this is a `def`).
  // We need this because implementations of `IPactStubber` might want to have their own state about the server running.
  implicit def serverInstance: IPactStubber =
    new PactServer

  implicit val scalaPactHttpClient: IScalaPactHttpClient[Task] =
    new ScalaPactHttpClient(Http4sClientHelper.doRequest)

  implicit val resultPublisher: IResultPublisher = new ResultPublisher(Http4sClientHelper.doRequest)
}
