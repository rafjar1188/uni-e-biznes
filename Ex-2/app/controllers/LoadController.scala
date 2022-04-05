package controllers

import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import repository.{AlbumRepository, ArtistRepository, SongRepository}

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class LoadController @Inject() (songRepo: SongRepository, albumRepo: AlbumRepository, artistRepo: ArtistRepository, cc: ControllerComponents) (implicit exec: ExecutionContext) extends AbstractController(cc) {

	def initialize: Action[AnyContent] = Action {
		val coma = artistRepo.add("Coma")
		val mata = artistRepo.add("Mata")
		val tede = artistRepo.add("TEDE")

		val comaAlbum1 = albumRepo.add("Czerwony album", coma.id)
		val comaAlbum2 = albumRepo.add("Pierwsze wyjście z mroku", coma.id)
		val comaAlbum3 = albumRepo.add("Hipertrofia", coma.id)

		val comaSong1 = songRepo.add("Los cebula i krokodyle łzy", comaAlbum1.id)
		val comaSong2 = songRepo.add("Angela", comaAlbum1.id)
		val comaSong3 = songRepo.add("Białe krowy", comaAlbum1.id)
		val comaSong4 = songRepo.add("Na pół", comaAlbum1.id)
		val comaSong5 = songRepo.add("Sto tysięcy jednakowych miast", comaAlbum2.id)
		val comaSong6 = songRepo.add("Leszek żukowski", comaAlbum2.id)

		val mataAlbum1 = albumRepo.add("100 dni do matury", mata.id)
		val mataAlbum2 = albumRepo.add("Młody matczak", mata.id)

		val mataSong1 = songRepo.add("Schodki", mataAlbum1.id)
		val mataSong2 = songRepo.add("Patointeligencja", mataAlbum1.id)

		Ok
	}

}
