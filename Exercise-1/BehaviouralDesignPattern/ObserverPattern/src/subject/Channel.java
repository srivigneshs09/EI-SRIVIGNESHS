package subject;

import observer.Observer;
import observer.Subject;
import java.util.ArrayList;
import java.util.List;

public class Channel implements Subject {
    private String name;
    private List<Observer> subscribers = new ArrayList<>();

    public Channel(String name) {
        this.name = name;
        System.out.println(name+" Youtube Channel Created");
        System.out.println();
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
        System.out.println(observer + " subscribed.");
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
        System.out.println(observer + " unsubscribed.");
    }

    @Override
    public void notifySubscribers(String videoTitle) {
        for (Observer subscriber : subscribers) {
            subscriber.update(videoTitle);
        }
    }

    public void uploadVideo(String title) {
        System.out.println("\nNew video uploaded: \"" + title + "\"");
        notifySubscribers(title);
    }
}
