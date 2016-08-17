import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class LinkTrackerSpec extends FlatSpec with Matchers {
  val res1 = LinkTracker.parseDoc(Sample1.originalDoc)

  "The LinkTracker" should "count the total number of img/a paths in a document" in {
    res1.totalPaths should be(Sample1.expectedTotalPaths)
  }

  it should "count the total unique img/a paths in a document" in {
    res1.uniquePaths should be(Sample1.expectedUniquePaths)
  }

  it should "provide a set of encoded paths for a document as a map of encoding id to path" in {
    res1.encodedPaths should be(Sample1.expectedEncodedPaths)
  }

}
