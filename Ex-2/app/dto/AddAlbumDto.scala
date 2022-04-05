package dto

import play.api.libs.json.{Json, OFormat}

case class AddAlbumDto(
                      name: String,
                      artistId: Int
                      )


object AddAlbumDto {
	implicit val format: OFormat[AddAlbumDto] = Json.using[Json.WithDefaultValues].format[AddAlbumDto]
}