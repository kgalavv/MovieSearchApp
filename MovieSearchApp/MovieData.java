/**
 * Methods to be implemented by the MovieData class
 */
interface MovieDataInterface {

	/**
	 * This method returns the movie's title
	 * 
	 * @return movie's title
	 */
	public String getTitle();

	/**
	 * This method returns the movie's rating
	 * 
	 * @return movie's rating
	 */
	public double getRating();

	/**
	 * This method returns the movie's publishing year
	 * 
	 * @return movie's publishing year
	 */
	public int getYear();

	/**
	 * This method returns the movie's genre
	 * 
	 * @return movie's genre
	 */
	public String getGenre();
}

/**
 * This class contains the necessary implementation to store data of individual
 * movies
 */
public class MovieData implements MovieDataInterface, Comparable<MovieData>  {
	protected String title;
	protected double rating;
	protected int year;
	protected String genre;

	/**
	 * Constructor method so as to create a MovieData object assigned with the movie's title,
	 * rating, year, and genre
	 *
	 * @param title - the title of the movie
	 * @param rating - the rating of the movie
	 * @param year - the year in which the movie was released
	 * @param genre - the genre of the movie
	 */
	public MovieData(String title, double rating, int year, String genre) {
		this.title = title;
		this.rating = rating;
		this.year = year;
		this.genre = genre;
	}

	/**
	 * This method returns the movie's title
	 * 
	 * @return movie's title
	 */
	@Override
	public String getTitle() {
		return this.title;
	}

	/**
	 * This method returns the movie's rating
	 * 
	 * @return movie's rating
	 */
	@Override
	public double getRating() {
		return this.rating;
	}

	/**
	 * This method returns the movie's publishing year
	 * 
	 * @return movie's publishing year
	 */
	@Override
	public int getYear() {
		return this.year;
	}

	/**
	 * This method returns the movie's genre
	 * 
	 * @return movie's genre
	 */
	@Override
	public String getGenre() {
		return this.genre;
	}

	/**
	 * This method allows comparision of two MovieData instances based on the movie's rating so as
	 * to determine if one is greater, smaller, or the same as the other. If two movie's have the
	 * same rating, then the movies are compared based on their title. Such comparision allows
	 * for insertion of MovieData instances in a Red Black Tree structure.
	 *
	 * @param otherMovie - the other MovieData instance being compared
	 * @return 1 if the MovieData passed is smaller than the current MovieData instance, -1 if the
	 *         MovieData passed is larger than the current MovieData instance, and 0 if the
	 *         MovieData passed is the same as the current MovieData instance
	 */
	@Override
	public int compareTo(MovieData otherMovie) {
		if (otherMovie == null) {
			throw new NullPointerException();
		}

		// comparing the MovieData instances on the basis of their ratings. If they have the same
		// rating, we proceed to compare the instances based on their titles
		if (otherMovie.getRating() > this.rating) {
			return -1;
		} else if (otherMovie.getRating() < this.rating) {
			return 1;
		} else {
			if (otherMovie.getTitle().compareTo(this.title) < 0) {
				return 1;
			} else if (otherMovie.getTitle().compareTo(this.title) > 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

// placeholders for the MovieData class

class MovieDataPlaceholderA implements MovieDataInterface {

	public String getTitle() {
		return "Marvel Studio's Movie";
	}

	public double getRating() {
		return 8.7;
	}

	public int getYear() {
		return 2019;
	}

	public String getGenre() {
		return "Action/Science Fiction";
	}
}

class MovieDataPlaceholderB implements MovieDataInterface {

	public String getTitle() {
		return "Warner Bros Movie";
	}

	public double getRating() {
		return 6.6;
	}

	public int getYear() {
		return 2021;
	}

	public String getGenre() {
		return "Thriller/Science Fiction";
	}
}

class MovieDataPlaceholderC implements MovieDataInterface {

	public String getTitle() {
		return "Fox Studio's Movie";
	}

	public double getRating() {
		return 5.3;
	}

	public int getYear() {
		return 2019;
	}

	public String getGenre() {
		return "Family/Comedy";
	}
}