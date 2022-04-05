package controllers

import dto.{AddAlbumDto, UpdateAlbumDto}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repository.{AlbumRepository, ArtistRepository}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class AlbumController @Inject()(albumRepo: AlbumRepository, artistRepo: ArtistRepository, cc: ControllerComponents)(implicit exec: ExecutionContext) extends AbstractController(cc) {

	def getAll: Action[AnyContent] = Action {
		Ok(Json.toJson(albumRepo.getAll))
	}

	def getById(id: Int): Action[AnyContent] = Action {
		val album = albumRepo.getById(id)

		if (album.isDefined) {
			Ok(Json.toJson(album))
		} else {
			NotFound(s"Artist with given id = $id not found")
		}
	}

	def add = Action(parse.json) { implicit req =>
		req.body.validate[AddAlbumDto].map(
			dto => {
				if (artistRepo.checkIfExists(dto.artistId)) {
					Ok(Json.toJson(albumRepo.add(dto.name, dto.artistId)))
				} else {
					BadRequest(s"Artist with given id = ${dto.artistId} not found")
				}
			}
		).recoverTotal {
			_ => BadRequest("Missing required fields: name, albumId")
		}

	}


	def update(id: Int) = Action(parse.json) { implicit req =>
		req.body.validate[UpdateAlbumDto].map(
			dto => {
				try {
					if (dto.artistId.isDefined && !artistRepo.checkIfExists(dto.artistId.get)) {
						BadRequest(s"Artist with id = ${dto.artistId.get} not found")
					} else {
						albumRepo.update(id, dto)
						Ok
					}
				} catch {
					case e: Exception => BadRequest(e.getMessage)
				}
			}
		).recoverTotal(_ => BadRequest("Bad request"))
	}


	def delete(id: Int): Action[AnyContent] = Action {
		try {
			albumRepo.delete(id)
			Ok
		} catch {
			case e: Exception => BadRequest(e.getMessage)
		}

	}

}
