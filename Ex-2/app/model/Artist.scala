package model

import play.api.libs.json.{Json, OFormat}


case class Artist(
                   id: Int,
                   var name: String,
                 )

object Artist {
  implicit val artistFormat: OFormat[Artist] = Json.using[Json.WithDefaultValues].format[Artist]
}