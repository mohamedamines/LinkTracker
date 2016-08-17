import scala.xml._

object LinkTracker {
  def parseDoc(doc: Elem): PathTrackerResult = {
    var id = 0
    var idA = 0
    var list: List[String] = List.empty
    var totalPath = 0
    var uniquePath = 0
    var map: Map[Int, String] = Map.empty

  // this function retrieves nodes for the mentioned element and attribute
  def getElements(doc:Elem, element:String, attribute:String) = {
    (doc \\ element).foreach { node =>
      (node \ attribute).foreach { elem =>
      list ::= elem.text
      totalPath = list.size
      uniquePath = list.distinct.size
      }
    }
  }
  getElements(doc, "a", "@href")
  list.distinct.foreach { elem =>
    if (!elem.contains("internal") && elem.contains("cust")) {
      if(!map.get(id).isDefined) {
        map += id -> elem
        idA = id
      }
      id += 7
    }
  }
  getElements(doc, "img", "@src")
    list.distinct.foreach { elem =>
      if (!elem.contains("internal") && elem.contains("cust")) {
        if(!map.get(id).isDefined) {
          map += id -> elem
        }
      }
    }


  // make the xml transformation for the 'a' attribute
  import scala.xml.transform._
  val rule1 = new RewriteRule {
    override def transform(n: Node) = n match {
      case e @ <a>{_*}</a> => e.asInstanceOf[Elem] %
      Attribute(null, "href", idA.toString, Null)
      case _ => n
    }
  }
  val xml2 = new RuleTransformer(rule1).transform(doc)
  PathTrackerResult(xml2.toString, map, uniquePath, totalPath)
  }
}

case class PathTrackerResult (trackedDoc: String, encodedPaths: Map[Int, String],
                              uniquePaths: Int, totalPaths: Int)
