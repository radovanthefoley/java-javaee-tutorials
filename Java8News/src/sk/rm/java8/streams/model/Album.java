package sk.rm.java8.streams.model;

import java.util.Arrays;
import java.util.List;

public class Album {

	public static enum Genre {
		POP, ROCK, CLASSICAL
	}

	public String name;

	public Genre genre;

	public List<Track> tracks;

	public Album(String name, Genre genre, List<Track> tracks) {
		this.name = name;
		this.genre = genre;
		this.tracks = tracks;
	}

	public static class Track {

		public int rating;

		public Track(int rating) {
			super();
			this.rating = rating;
		}
	}

	public static List<Album> getDefaultAlbumCollection() {
		return Arrays.asList(
				new Album("La Roux - Trouble in Paradise", Genre.POP,
						Arrays.asList(new Track(2), new Track(3), new Track(0), new Track(1), new Track(3))),
				new Album("KISS - Sonic Boom", Genre.ROCK,
						Arrays.asList(new Track(3), new Track(3), new Track(3), new Track(3))),
				new Album("AC/DC - Back in Black", Genre.ROCK,
						Arrays.asList(new Track(5), new Track(3), new Track(4), new Track(2), new Track(3))),
				new Album("Beethoven", Genre.CLASSICAL, Arrays.asList(new Track(5), new Track(3), new Track(1))),
				new Album("Taylor Swift - 1989", Genre.POP,
						Arrays.asList(new Track(2), new Track(3), new Track(0), new Track(1))),
				new Album("Calvin Harris - Motion", Genre.POP,
						Arrays.asList(new Track(2), new Track(3), new Track(0), new Track(1), new Track(3))),
				new Album("Led Zeppelin - Led Zeppelin IV", Genre.ROCK,
						Arrays.asList(new Track(4), new Track(5), new Track(3), new Track(3), new Track(4)))

		);

	}

}
