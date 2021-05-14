import java.util.ArrayList;

public class TheTweeter {
    private ArrayList<TweetClass> twitter;

    @Override
    public String toString() {
        return "TheTweeter{" +
                "twitter=" + twitter +
                '}';
    }

    public TheTweeter(ArrayList<TweetClass> twitter) {
        this.twitter = twitter;
    }

    public void add(TweetClass t) {
        twitter.add(t);
    }

    public void remove(String author) {
        for(int i = 0; i < twitter.size(); i++) {
            if((twitter.get(i).getAuthor()).equals(author)) {
                twitter.remove(i);
            }
        }
    }

    //Returns one TweetClass object who's author is author
    //Check all tweets twitter
    //If there is no author, return null otherwise
    public TweetClass get(String author) {
        for(int i = 0; i<twitter.size(); i++) {
            if((twitter.get(i).getAuthor()).equals(author)) {
                return twitter.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {


        TweetClass tweet = new TweetClass("Aaron", "I am doing a problem set");
        TweetClass tweetTwo = new TweetClass("Zoe", "I am working out today");
        TweetClass tweetThree = new TweetClass("Peter", "Recursion is a big Dill");
        TweetClass tweetFour = new TweetClass("Lauren", "Hey");
        TweetClass tweetFive = new TweetClass("Arden", "I like to be on my phone");
        TweetClass tweetSix = new TweetClass("Mitja", "Blah");

        TheTweeter twitterTest = new TheTweeter(new ArrayList<TweetClass>());

        twitterTest.add(tweet);
        twitterTest.add(tweetTwo);
        twitterTest.add(tweetThree);
        twitterTest.add(tweetFour);
        twitterTest.add(tweetFive);
        twitterTest.add(tweetSix);

        System.out.println("Add");
        System.out.println(twitterTest);

        System.out.println("Remove");
        twitterTest.remove("Aaron");
        twitterTest.remove("Mitja");

        System.out.println(twitterTest);

        System.out.println("Get");
        System.out.println(twitterTest.get("Peter"));
        System.out.println(twitterTest.get("Sam"));
        System.out.println(twitterTest.get("Zoe"));

    }


}
