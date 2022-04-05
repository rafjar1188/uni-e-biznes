package repository

import dto.AddUpdateArtistDto
import model.{Album, Artist}
import services.Counter

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ListBuffer


@Singleton
class ArtistRepository @Inject()(counter: Counter, albumRepo: AlbumRepository) {

	private val artists = new ListBuffer[Artist]()

	def getAll: List[Artist] = artists.toList

	def getById(id: Int): Option[Artist] = artists.find(artist => artist.id == id)

	def add(name: String): Artist = {
		val newArtist = new Artist(counter.nextCount(), name)
		artists.addOne(newArtist)
		newArtist
	}

	def delete(id: Int) = {
		val artist = getById(id).getOrElse(throw new NoSuchElementException("Artist not found"))
		artists.remove(artists.indexOf(artist))
		albumRepo.deleteByArtistId(artist.id)
	}

	def update(id: Int, newName: String) = {
		val artist = getById(id).getOrElse(throw new NoSuchElementException("Artist not found"))
		artist.name = newName
	}

	def checkIfExists(id: Int): Boolean = getById(id).isDefined

}
