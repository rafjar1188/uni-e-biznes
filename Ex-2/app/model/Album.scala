package model

import play.api.libs.json.{Json, OFormat}


case class Album(
                id: Int,
                var name: String,
                var artistId: Int,
                )

object Album {
  implicit val albumFormat: OFormat[Album] = Json.using[Json.WithDefaultValues].format[Album]
}