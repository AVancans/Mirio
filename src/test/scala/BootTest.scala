import org.scalatest.FlatSpec

import app.Boot
import org.scalatest.junit.JUnitRunner

class BootTest extends FlatSpec {

  "This test" should " pass " in {
    assert(1 + 1 === 2, "this is a clue")
  }

}
