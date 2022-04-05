package repository

import dto.{UpdateAlbumDto, UpdateSongDto}
import model.Song
import play.api.mvc.Action
import services.Counter

import javax.inject.{Inject, Singleton}
import scala.collection.mutable.ListBuffer

@Singleton
class SongRepository @Inject() (counter: Counter) {
	private val songs = new ListBuffer[Song]()

	def getAll: List[Song] = songs.toList

	def getById(id: Int): Option[Song] = songs.find(song => song.id == id)

	def add(title: String, albumId: Int): Song = {
		val newSong = new Song(counter.nextCount(), title, albumId)
		songs.addOne(newSong)
		newSong
	}


	def delete(id: Int): Song = {
		val songToDelete = getById(id).getOrElse(throw new IllegalArgumentException("Song not found"))
		songs.remove(songs.indexOf(songToDelete))
	}

	def deleteByAlbumId(id: Int): Unit = songs.filter(song => song.albumId == id).foreach(song => delete(song.id))

	def update(id: Int, dto: UpdateSongDto): Unit = {
		val song = getById(id).getOrElse(throw new NoSuchElementException("Song not found"))

		dto.title.foreach(newTitle => song.title = newTitle)
		dto.albumId.foreach(newAlbumId => song.albumId = newAlbumId)
	}
}
