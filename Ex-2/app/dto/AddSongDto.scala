package dto

import play.api.libs.json.{Json, OFormat}

case class AddSongDto(title: String, albumId: Int)

object AddSongDto {
	implicit val format: OFormat[AddSongDto] = Json.using[Json.WithDefaultValues].format[AddSongDto]
}