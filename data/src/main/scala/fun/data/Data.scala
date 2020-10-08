package fun.data

case class Parameters(
  dataset: List[String],
  timezone: String,
  rows: Double,
  format: String,
  `geofilter.distance`: List[String],
  facet: List[String]
)
case class Fields(
  station_state: String,
  dist: String,
  densitylevel: String,
  nbbikeoverflow: Int,
  maxbikeoverflow: Int,
  nbedock: Int,
  station_name: String,
  kioskstate: String,
  nbfreeedock: Int,
  station_type: String,
  station_code: String,
  creditcard: String,
  station: String,
  nbebike: Int,
  duedate: String,
  nbebikeoverflow: Int,
  nbfreedock: Int,
  overflow: String,
  nbdock: Int,
  geo: List[Double],
  overflowactivation: String,
  nbbike: Int
)
case class Geometry(
  `type`: String,
  coordinates: List[Double]
)
case class Record(
  datasetid: String,
  recordid: String,
  fields: Fields,
  geometry: Geometry,
  record_timestamp: String
)
case class Facets(
  name: String,
  path: String,
  count: Double,
  state: String
)
case class FacetGroup(
  name: String,
  facets: List[Facets]
)
case class VelibData(
  nhits: Double,
  parameters: Parameters,
  records: List[Record],
  facet_groups: List[FacetGroup]
)
