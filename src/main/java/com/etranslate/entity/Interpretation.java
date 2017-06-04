package com.etranslate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by yancychan on 17-6-4.
 */
@Entity
@Table(name = "interpretation")
public class Interpretation {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id", columnDefinition = "varchar(64) binary")
    private String id;

    @Column(name = "url")
    private String url;

    @Column(name = "translate_id")
    private int translateId;

    @Column(name = "parephrase")
    private String parephrase;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTranslateId() {
        return translateId;
    }

    public void setTranslateId(int translateId) {
        this.translateId = translateId;
    }

    public String getParephrase() {
        return parephrase;
    }

    public void setParephrase(String parephrase) {
        this.parephrase = parephrase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
