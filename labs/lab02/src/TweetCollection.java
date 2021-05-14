import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TweetCollection {

    private ArrayList<TweetClass> tweets;

    public TweetCollection(ArrayList<TweetClass> tweets) {
        this.tweets = tweets;
    }

    //A Method that given an author, returns all the tweets for the given author
    public ArrayList<TweetClass> getAuthorTweets(String author) {
        ArrayList<TweetClass> authorList = new ArrayList<>();

//        for(int i = 0; i < tweets.size(); i++) {
//            if ((tweets.get(i).getContent()).equals(author)) {
//                authorList.add(tweets.get(i));
//            }
//        }

        for(TweetClass t: this.tweets) {
            if(t.getAuthor().equals(author)) {
                authorList.add(t);
            }
        }
        return authorList;
    }

    //A Method that given content, returns all given content related to the input.
    public ArrayList<TweetClass> getContentTweets(String content) {
        ArrayList<TweetClass> contentList = new ArrayList<>();

//        for(int i = 0; i < tweets.size(); i++) {
//            if((tweets.get(i).getContent()).equals(content)) {
//                contentList.add(tweets.get(i));
//            }
//        }

        for(TweetClass t: this.tweets) {
            if(t.getContent().equals(content)) {
                contentList.add(t);
            }
        }

        return contentList;
    }

    //A method that returns true or false if an author wrote about specific content.
    public boolean tweetedAbout(String author, String content){
        ArrayList<TweetClass> tweeeeetAbout = getAuthorTweets(author);
        for(TweetClass t: tweeeeetAbout){
            if(t.contains(content)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayList<TweetClass> tweets = new ArrayList<>();
        //TweetClass tweeeets = new TweetClass()
        TweetCollection test = new TweetCollection(tweets);

        try {
            // Step 1: create a scanner
            System.out.println("we're going to create a scanner");
            // From a URL
            URL url = new URL("http://homes.soic.indiana.edu/classes/fall2020/csci/c343-mitja/test2020/tweet-data-September10.txt");
            System.out.println("obtained a URL");
            Scanner in = new Scanner(url.openStream());
            System.out.println("scanner created");
            // From System.in (user's inputs)
            // Scanner in = new Scanner(System.in);
            // From a local file (e.g., tweet-data-September10.txt on your local machine)
            // Scanner in = new Scanner(new FileReader("tweet-data-September10.txt"));
            // Step 2: read data
            int i = 0;
            while (in.hasNext()) {
                //nextLine() reads a line;
                //Scanner class has other methods to allow the user to read values of various types, eg.., nextInt()
                String st = in.nextLine();
                String author = st.substring(0,st.indexOf(" "));
                String content = st.substring(st.indexOf(" ") , st.length());
                TweetClass tmp = new TweetClass(author, content);
                test.tweets.add(tmp);

                String str = in.nextLine();
                System.out.print("at line ");
                System.out.print(i);
                System.out.print(" there is [");
                System.out.print(str);
                System.out.println("]");
                i = i + 1;
            }

            //Read the url and add it to the tweets ArrayList in main
            //split content and author
            //S = "This is a string"
            //S.split(" ", 2);
            //{"This", "is a string"}
            //Create a new tweetclass for each line read
            //Split with space, split(" ", 2)

            System.out.println();
            System.out.println("Testing Methods: ");
            System.out.println();

            System.out.println("Author");
            ArrayList<TweetClass> getAuthor = test.getAuthorTweets("NoSQLDigest");
            for(TweetClass k: getAuthor){
                System.out.println(k);
            }

            System.out.println();

            System.out.println("Content");
            ArrayList<TweetClass> getContent = test.getContentTweets("data");
            for(TweetClass j: getContent) {
                System.out.println(j);
            }

            System.out.println();

            System.out.println("Tweeted About");
            System.out.println(test.tweetedAbout("NoSQLDigest", "Data"));

            //Step 3: close the scanner
            in.close();

        } catch (Throwable e) {
            System.out.println("exception is " + e);
            e.printStackTrace();
        }

        //Make a storeURL array list, and add the url into the arrayList.
        //Then, put that list into the tweetcollection instantiation so I can read it.

    }
}
