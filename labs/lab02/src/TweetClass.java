public class TweetClass {

    private String author;
    private String content;

    public TweetClass(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TweetClass{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    //Contains method to see if the content is larger then 0 characters.
    //False if it isn't, true if it is.
    public boolean contains(String s) {
        if(getContent().contains(s)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        TweetClass tweet = new TweetClass("Aaron", "I am doing a problem set");
        TweetClass tweetTwo = new TweetClass("Zoe", "I am working out today");
        TweetClass tweetThree = new TweetClass("Peter", "Recursion is a big Dill");

        //Testing TwoString and Contains Methods
        System.out.println(tweet.toString());
        System.out.println(tweetTwo.contains("Smartphone"));
        System.out.println(tweetThree.contains("Recursion is a big Dill"));

    }
}