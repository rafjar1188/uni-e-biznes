package dto

import play.api.libs.json.{Json, OFormat}

case class UpdateAlbumDto(
                         name: Option[String],
                         artistId: Option[Int]
                         )

object UpdateAlbumDto {
	implicit val format: OFormat[UpdateAlbumDto] = Json.using[Json.WithDefaultValues].format[UpdateAlbumDto]
}