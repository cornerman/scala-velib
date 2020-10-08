package fun.app

import cats.effect.SyncIO
import outwatch.OutWatch

object Main {
  def main(args: Array[String]) = {
    val app = OutWatch.renderInto[SyncIO]("#app", Component.root)

    app.unsafeRunSync()
  }
}
