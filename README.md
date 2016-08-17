# LinkTracker

  Given an HTML document as Scala XML:
 
  For each a element with an href attribute or img element with a src attribute
  in the document if the attribute value contains "cust" but not "internal"
  replace it with a unique numeric ID.  The IDs should start at 0 and increment
  by 7.  If the path matches one we've already seen reuse the old ID.
 
  Return the updated HTML document along with a Map of IDs to paths wrapped in
  a PathTrackerResult.
