package dto

import play.api.libs.json.{Json, OFormat}

case class UpdateSongDto(
	                        title: Option[String],
	                        albumId: Option[Int]
                        )


object UpdateSongDto {
	implicit val format: OFormat[UpdateSongDto] = Json.using[Json.WithDefaultValues].format[UpdateSongDto]
}