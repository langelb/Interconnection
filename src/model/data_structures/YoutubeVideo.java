package model.data_structures;

import java.util.Date;

public class YoutubeVideo implements Comparable<YoutubeVideo> {
    private String id;
    private Date trendingDate;
    private String title;
    private int likes;

    public YoutubeVideo(String id, Date trendingDate, String title, int likes) {
        this.id = id;
        this.trendingDate = trendingDate;
        this.title = title;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public Date getTrendingDate() {
        return trendingDate;
    }

    public String getTitle() {
        return title;
    }

    public int getLikes() {
        return likes;
    }

    @Override
    public int compareTo(YoutubeVideo o) {
        return Integer.compare(this.likes, o.likes);
    }
}
