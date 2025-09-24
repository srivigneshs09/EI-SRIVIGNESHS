import observer.Subscriber;
import subject.Channel;

public class Main {
    public static void main(String[] args) {
        // Create a YouTube channel
        Channel channel = new Channel("Tech with Sri Vignesh S");

        // Create subscribers
        Subscriber dad = new Subscriber("Dad");
        Subscriber mom = new Subscriber("Mom");
        Subscriber sister = new Subscriber("Sister");

        // Subscribing to channel
        channel.subscribe(dad);
        channel.subscribe(mom);
        channel.subscribe(sister);

        // Uploading videos
        channel.uploadVideo("Design Patterns in Java");
        channel.uploadVideo("Observer Pattern Explained");

        // Unsubscribing
        channel.unsubscribe(mom);

        // Uploading another video
        channel.uploadVideo("Command Pattern Example");
    }
}
