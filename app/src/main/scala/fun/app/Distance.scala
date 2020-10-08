package fun.app

object Distance {
  // https://en.wikipedia.org/wiki/Haversine_formula
  def meter(lat1: Double, lon1: Double)(lat2: Double, lon2: Double): Double = {
    val r = 6371 * 1000 // Radius of the earth in meter
    val dLat = deg2rad(lat2 - lat1)
    val dLon = deg2rad(lon2 - lon1)
    val a = Math.sin(dLat / 2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))

    r * c
  }

  def deg2rad(deg: Double): Double = deg * Math.PI / 180
}
