package repository

import dto.UpdateAlbumDto
import model.{Album, Song}
import services.Counter

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ListBuffer

@Singleton
class AlbumRepository @Inject()(counter: Counter, songRepo: SongRepository) {

	private val albums = new ListBuffer[Album]

	def getAll: List[Album] = albums.toList

	def getById(id: Int): Option[Album] = albums.find(a => a.id == id)

	def add(name: String, artistId: Int): Album = {
		val album = new Album(counter.nextCount(), name, artistId)
		albums.addOne(album)
		album
	}

	def delete(id: Int) = {
		val album = getById(id).getOrElse(throw new IllegalArgumentException("Album not found"))
		albums.remove(albums.indexOf(album))
		songRepo.deleteByAlbumId(album.id)
	}

	def deleteByArtistId(id: Int) = albums.filter(a => a.artistId == id).foreach(a => delete(a.id))

	def update(id: Int, dto: UpdateAlbumDto) = {
		val album = getById(id).getOrElse(throw new NoSuchElementException("Album was not found"))

		dto.name.foreach(newName => album.name = newName)
		dto.artistId.foreach(newArtistId => album.artistId = newArtistId)
	}

	def checkIfExists(id: Int): Boolean = getById(id).isDefined

}
