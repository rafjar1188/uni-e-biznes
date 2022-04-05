package controllers

import dto.AddUpdateArtistDto
import model.Artist
import play.api.Logger
import play.api.libs.json.{JsError, JsSuccess, JsValue, Json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repository.ArtistRepository

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

@Singleton
class ArtistController @Inject()(artistRepo: ArtistRepository, cc: ControllerComponents)(implicit exec: ExecutionContext) extends AbstractController(cc) {

	val logger: Logger = Logger(classOf[ArtistController])

	def getById(id: Int): Action[AnyContent] = Action {

		val artist = artistRepo.getById(id)

		if (artist.isDefined) {
			Ok(Json.toJson(artist))
		} else {
			NotFound(s"Artist with given id = $id not found")
		}
	}

	def getAll: Action[AnyContent] = Action {
		Ok(Json.toJson(artistRepo.getAll))
	}

	def add(): Action[JsValue] = Action(parse.json) { implicit req =>
		req.body.validate[AddUpdateArtistDto].map(
			dto => Ok(Json.toJson(artistRepo.add(dto.name)))
		).recoverTotal {
			e => BadRequest(s"Missing required field: name")
		}
	}


	def update(id: Int): Action[JsValue] = Action(parse.json) { implicit req =>
		req.body.validate[AddUpdateArtistDto].map(
			dto => {
				try {
					artistRepo.update(id, dto.name)
					Ok
				} catch  {
					case e: Exception => BadRequest(e.getMessage)
				}
			}
		).recoverTotal {
			_ => BadRequest("Required field name not provided")
		}
	}


	def delete(id: Int): Action[AnyContent] = Action {
		try {
			artistRepo.delete(id)
			Ok("Artist removed")
		} catch {
			case ex: Exception => BadRequest(ex.getMessage)
		}
	}

}
