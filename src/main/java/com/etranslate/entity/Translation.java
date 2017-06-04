package com.etranslate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yancychan on 17-6-4.
 */
@Entity
@Table(name = "translation")
public class Translation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(64) binary")
    private String tId;

    @Column(name = "word")
    private String word;

    @Column(name = "translate_text")
    private String translateText;

    @Column(name = "upvote")
    private int upvote;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslateText() {
        return translateText;
    }

    public void setTranslateText(String translateText) {
        this.translateText = translateText;
    }

    public int getUpvote() {
        return upvote;
    }

    public void setUpvote(int upvote) {
        this.upvote = upvote;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Translation{" +
                "tId='" + tId + '\'' +
                ", word='" + word + '\'' +
                ", translateText='" + translateText + '\'' +
                ", upvote=" + upvote +
                ", user=" + user +
                '}';
    }
}
