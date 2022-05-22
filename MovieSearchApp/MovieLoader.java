import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;

/**
 * Methods to be implemented by the MovieLoader class
 */
interface MovieLoaderInterface {

    /**
     * This method reads in data about movies from the passed path to a CSV file,
     * stores all this data in a list, and finally returns this list
     *
     * @param csvFilePath - path of the CSV file containing the movie data
     * @return a list containing all the movies with its respective data as read in
     *         from the passed CSV file
     * @throws FileNotFoundException if the CSV file does not exist at the specified
     *                               path
     */
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException;

    /**
     * This method reads in data about movies from the passed path to a directory
     * which contains CSV file(s) with movie data, stores all this data in a list,
     * and finally returns this list containing data about the movies present in the
     * CSV file(s) in the passed directory
     *
     * @param directoryPath - path to a directory containing CSV file(s) with movie
     *                      data
     * @return a list containing all the movies with its respective data as read in
     *         from the CSV file(s) present in the passed directory
     * @throws FileNotFoundException if the directory does not exist at the
     *                               specified path
     */
    public List<MovieDataInterface> loadFilesInDirectory(String directoryPath)
            throws FileNotFoundException;
}

/**
 * This class contains the necessary implementation to load the data of movies
 * from a path to a CSV file storing data for movies or a path to a directory
 * which has CSV file(s) which store the data for movies
 */
public class MovieLoader implements MovieLoaderInterface {

    /**
     * This method reads in data about movies from the passed path to a CSV file,
     * stores all this data in a list, and finally returns this list
     *
     * @param csvFilePath - path of the CSV file containing the movie data
     * @return a list containing all the movies with its respective data as read in
     *         from the passed CSV file
     * @throws FileNotFoundException if the CSV file does not exist at the specified
     *                               path
     */
    @Override
    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        List<MovieDataInterface> moviesList = new LinkedList<MovieDataInterface>();
        File movieFile = new File(csvFilePath);
        Scanner fileScnr = new Scanner(movieFile);
        int indexTitle = 0;
        int indexGenre = 0;
        int indexYear = 0;
        int indexRating = 0;
        // obtaining the column names
        String colNames = fileScnr.nextLine();
        String[] cols = colNames.split(",");
        // index of the fields are found
        for (int i = 0; i < cols.length; ++i) {
            if (cols[i].equals("Title")) {
                indexTitle = i;
            }

            if (cols[i].equals("Genre")) {
                indexGenre = i;
            }

            if (cols[i].equals("Premiere")) {
                indexYear = i;
            }

            if (cols[i].equals("IMDB Score")) {
                indexRating = i;
            }
        }

        // reading in the rows of the dataset
        while (fileScnr.hasNextLine()) {
            String readRow = fileScnr.nextLine();
            String[] rowContent = readRow.split(",");

            // to count the number of quotes present in a single field of data
            for (int i = 0; i < rowContent.length; ++i) {
             int numQuotes = 0;
             for (int j = 0; j < rowContent[i].length(); ++j) {
             if (rowContent[i].charAt(j) == '"') {
             numQuotes += 1;
             }
             }

             // if the number of quotes in a single field of data is even, we are not required to
             // make any changes
             if (numQuotes % 2 == 0) {
             }
             // if the quotes in a single field of data is not balanced, we are required to adjust
             // the array storing the row data so that the quotes become balanced
             else {
             rowContent[i + 1] = rowContent[i].concat(",").concat(rowContent[i + 1]);
             }
             }

            ArrayList<String> rowData = new ArrayList<String>(Arrays.asList(rowContent));

            String title = null;
            String genre = null;
            int year = -1;
            double rating = -1;

            // extracting the fields from the captured data of the rows
            if (rowData.get(indexTitle).substring(0,1).equals("\"")) {
                title = rowData.get(indexTitle + 1).substring(1, rowData.get(indexTitle +
                        1).length());
                genre = rowData.get(indexGenre + 1);
                year = Integer.parseInt(rowData.get(indexYear + 2).trim().substring(rowData.
                        get(indexYear + 2).length() - 5).substring(0, 4));
                rating = Double.parseDouble(rowData.get(indexRating + 2));
            } else if (!(rowData.get(indexYear).substring(0,1).equals("\""))) {
                title = rowData.get(indexTitle);
                genre = rowData.get(indexGenre);
                year = Integer.parseInt(rowData.get(indexYear).substring(rowData.get(indexYear).
                        length() - 4));
                rating = Double.parseDouble(rowData.get(indexRating));
            } else {
                title = rowData.get(indexTitle);
                genre = rowData.get(indexGenre);
                year = Integer.parseInt(rowData.get(indexYear + 1).substring(rowData.
                        get(indexYear + 1).length() - 5).substring(0, 4));
                rating = Double.parseDouble(rowData.get(indexRating+1));
            }

            if ((!(title == null)) && (!(genre == null)) && (!(year == -1)) && (!(rating == -1))) {
                MovieData singleMovieRowData = new MovieData(title, rating, year, genre);
                moviesList.add(singleMovieRowData);
            }
        }

        return moviesList;
    }

    /**
     * This method reads in data about movies from the passed path to a directory
     * which contains CSV file(s) with movie data, stores all this data in a list,
     * and finally returns this list containing data about the movies present in the
     * CSV file(s) in the passed directory
     *
     * @param directoryPath - path to a directory containing CSV file(s) with movie
     *                      data
     * @return a list containing all the movies with its respective data as read in
     *         from the CSV file(s) present in the passed directory
     * @throws FileNotFoundException if the directory does not exist at the
     *                               specified path
     */
    @Override
    public List<MovieDataInterface> loadFilesInDirectory(String directoryPath)
            throws FileNotFoundException {
        List<MovieDataInterface> moviesList = new LinkedList<MovieDataInterface>();
        File[] movieFiles = new File(directoryPath).listFiles();

        // we iterate over each of the files to be loaded and load each of them individually
        // using the loadFile() method
        if(!(movieFiles == null)) {
            for (int i = 0; i < movieFiles.length; ++i) {
                String csvFilePath = movieFiles[i].getAbsolutePath();
                if (csvFilePath.endsWith("csv")) {
                    moviesList.addAll(loadFile(csvFilePath));
                }
            }
            return moviesList;
        }
        // if the files to be loaded do not exist, a file not found exception is thrown
        else {
            throw new FileNotFoundException();
        }
    }
}

//placeholders for the MovieLoader class

class MovieLoaderPlaceholder implements MovieLoaderInterface {

    public List<MovieDataInterface> loadFile(String csvFilePath) throws FileNotFoundException {
        List<MovieDataInterface> list = new LinkedList<MovieDataInterface>();
        list.add(new MovieDataPlaceholderA());
        list.add(new MovieDataPlaceholderB());
        return list;
    }

    public List<MovieDataInterface> loadFilesInDirectory(String directoryPath)
            throws FileNotFoundException {
        List<MovieDataInterface> list = new LinkedList<MovieDataInterface>();
        list.add(new MovieDataPlaceholderA());
        list.add(new MovieDataPlaceholderB());
        list.add(new MovieDataPlaceholderC());
        return list;
    }
}