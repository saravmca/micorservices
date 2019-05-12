package com.techprimers.stock.dbservice.model;
import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="quotes",catalog="test")
@EntityListeners(AuditingEntityListener.class)
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private Integer id;
    @Column(name="user_name")
    private String userName;
    @Column(name="quote")
    private String quote;

    public Quote() {
    }

    public Quote(String userName, String quote) {
        this.userName = userName;
        this.quote = quote;
    }

    public Integer getId() {

        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {

        return userName;
    }
    public void setUserName(String userName) {

        this.userName = userName;
    }
    public String getQuote() {

        return quote;
    }
    public void setQuote(String quote) {

        this.quote = quote;
    }

}
