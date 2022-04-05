package dto

import play.api.libs.json.{Json, OFormat}

case class AddUpdateArtistDto(name: String)


object AddUpdateArtistDto {
  implicit val artistFormat: OFormat[AddUpdateArtistDto] = Json.using[Json.WithDefaultValues].format[AddUpdateArtistDto]
}