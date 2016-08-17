/**
  * Created by Freeware Sys on 8/17/2016.
  */
object Sample1 {
  val originalDoc =
<html>
  <head>
    <title>Some Doc</title>
    <link rel="stylesheet" href="/styles/mystyle.css"/>
    <link rel="stylesheet" href="/styles/custstyles/mystyle.css"/>
    <style type="text/css">
      {""".blah { background-image: url('/img/happycustomers.jpg'); }"""}
    </style>
  </head>
  <body>
    Some intro text about mypic and something.
    <a href="/something/about/customers">GO HERE</a>
    <img src="/img/mypic.jpg"/>
    <img src="/img/internalcustonly/mypic.jpg"/>
    <img src="/img/happycustomers.jpg"/>
    <img src="/img/mypic.jpg"/>
    <a href="/something/about/customers">OR HERE</a>
  </body>
</html>

  val expectedEncodedPaths = Map(
    0 -> "/something/about/customers",
    7 -> "/img/happycustomers.jpg"
  )

  val expectedUniquePaths = 4

  val expectedTotalPaths = 6

  val expectedDoc =
"""<html>
  <head>
    <title>Some Doc</title>
    <link rel="stylesheet" href="/styles/mystyle.css"/>
    <link rel="stylesheet" href="/styles/custstyles/mystyle.css"/>
    <style type="text/css">
      .blah { background-image: url('/img/happycustomers.jpg'); }
    </style>
  </head>
  <body>
    Some intro text about mypic and something.
    <a href="0">GO HERE</a>
    <img src="/img/mypic.jpg"/>
    <img src="/img/internalcustonly/mypic.jpg"/>
    <img src="7"/>
    <img src="/img/mypic.jpg"/>
    <a href="0">OR HERE</a>
  </body>
</html>"""
}
