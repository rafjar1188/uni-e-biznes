package dto

import play.api.libs.json.{Json, OFormat}

case class UpdateArtistDto(
                          id: Int,
                          name: String
                          )


object UpdateArtistDto {
	implicit val format: OFormat[UpdateArtistDto] = Json.using[Json.WithDefaultValues].format[UpdateArtistDto]
}