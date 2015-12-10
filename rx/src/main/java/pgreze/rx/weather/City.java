package pgreze.rx.weather;

import java.util.List;

/**
 * Example: <a href="https://api.github.com/repos/ReactiveX/RxJava">ReactiveX/RxJava</a>
 */
public class City {

    public int id;
    public int dt;
    public String name;
    public Sys sys;
    public List<Weather> weather;

    public static class Sys {
        public String country;
    }

    public static class Weather {
        public String main;
        public String description;
    }
}
