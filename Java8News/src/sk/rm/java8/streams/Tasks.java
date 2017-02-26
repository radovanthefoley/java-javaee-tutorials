package sk.rm.java8.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import sk.rm.java8.streams.model.Album;

public class Tasks {
	public static void main(String... args) {
		List<Album> albums = Album.getDefaultAlbumCollection();

		// TODO 1
		// use streams and lambdas to rewrite the following
		for (Album a : albums) {
			System.out.println(a.name);
		}

		// ---
		System.out.println("--- solution 1:");
		// ---

		// SOLUTION 1
		albums.stream().forEach(a -> System.out.println());

		// ---
		System.out.println();
		// ---

		// TODO 2
		// use streams and lambdas to rewrite the following
		// Hint: filter
		for (Album a : albums) {
			if (a.genre == Album.Genre.ROCK) {
				System.out.println(a.name);
			}
		}

		// ---
		System.out.println("--- solution 2:");
		// ---

		// SOLUTION 2

		// ---
		System.out.println();
		// ---

		// TODO 3
		// use streams and lambdas to rewrite the following
		// (prints album names that has at least
		// one song with rating 4+)
		// Hint: anyMatch
		for (Album a : albums) {
			boolean hasFavorite = false;
			for (Album.Track t : a.tracks) {
				if (t.rating >= 4) {
					hasFavorite = true;
					break;
				}
			}
			if (hasFavorite)
				System.out.println(a.name);
		}

		// ---
		System.out.println("--- solution 3:");
		// ---

		// SOLUTION 3
		
		// ---
		System.out.println();
		// ---

		// TODO 4
		// use streams and lambdas to rewrite the following
		// (creates map of album names where number of tracks is a key and
		// prints it)
		// Hint: collect
		Map<Long, String> albumsByTracks = new HashMap<>();
		for (Album a : albums) {
			long n = a.tracks.size();
			if (albumsByTracks.containsKey(n)) {
				albumsByTracks.put(n, albumsByTracks.get(n) + ", " + a.name);
			} else {
				albumsByTracks.put(n, a.name);
			}
		}
		for (long n : albumsByTracks.keySet()) {
			System.out.println(n + ": " + albumsByTracks.get(n));
		}

		// ---
		System.out.println("--- solution 4:");
		// ---

		// SOLUTION 4
		
		
		// ---
		System.out.println();
		// ---

		// TODO 5
		// use streams and lambdas to rewrite the following
		// (creates collection of albums that has no track
		// with rating worse than 3, sorts it, prints by names)
		// Hint: sorted, collect
		List<Album> worthItList = new ArrayList<>();
		for (Album a : albums) {
			boolean belongs = true;
			for (Album.Track t : a.tracks) {
				if (t.rating < 3) {
					belongs = false;
					break;
				}
			}
			if (belongs)
				worthItList.add(a);
		}
		Collections.sort(worthItList, new Comparator<Album>() {
			public int compare(Album a1, Album a2) {
				return a1.name.compareTo(a2.name);
			}
		});
		for (Album a : worthItList) {
			System.out.println(a.name);
		}

		// ---
		System.out.println("--- solution 5:");
		// ---

		// SOLUTION 5
		

		// ---
		System.out.println();
		// ---
	}
}
