package model

import play.api.libs.json.{Json, OFormat}

case class Song(
               id: Int,
               var title: String,
               var albumId: Int
               )

object Song {
  implicit val songFormat: OFormat[Song] = Json.using[Json.WithDefaultValues].format[Song]
}