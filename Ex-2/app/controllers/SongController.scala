package controllers

import dto.{AddSongDto, UpdateSongDto}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repository.{AlbumRepository, SongRepository}

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
case class SongController @Inject()(songRepo: SongRepository, albumRepo: AlbumRepository, cc: ControllerComponents)(implicit exec: ExecutionContext) extends AbstractController(cc) {

	def getAll: Action[AnyContent] = Action {
		Ok(Json.toJson(songRepo.getAll))
	}

	def getById(id: Int): Action[AnyContent] = Action {
		val song = songRepo.getById(id)

        if (song.isDefined) {
          Ok(Json.toJson(song))
        } else {
          NotFound(s"Song with given id = $id not found")
        }
	}

	def add: Action[JsValue] = Action(parse.json) { implicit req =>
		req.body.validate[AddSongDto].map(
			dto => {

				Ok(Json.toJson(songRepo.add(dto.title, dto.albumId)))
			}
		).recoverTotal {
			e => BadRequest(s"Error $e")
		}
	}

	def update(id: Int) = Action(parse.json) { implicit req =>
		req.body.validate[UpdateSongDto].map(dto => {
			try {
				if (dto.albumId.isDefined && !albumRepo.checkIfExists(dto.albumId.get)) {
					BadRequest(s"Album with id = ${dto.albumId.get} not found")
				} else {
					songRepo.update(id, dto)
					Ok
				}
			} catch {
				case e: Exception => BadRequest(e.getMessage)
			}
		}).recoverTotal(_ => BadRequest("Bad request"))
	}

	def delete(id: Int) = Action {
		try {
			songRepo.delete(id)
			Ok
		} catch {
			case e: Exception => BadRequest(e.getMessage)
		}
	}

}
