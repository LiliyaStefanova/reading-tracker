package org.elstere.booktrkr.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class GithubProject implements Serializable {

    @Id
    @GeneratedValue
    private Long id;


    private String orgName;

    @Column(unique=true)
    private String repoName;

    public GithubProject(){

    }


    public GithubProject(String orgName, String repoName){
        this.orgName = orgName;
        this.repoName = repoName;
    }

    public Long getId(){
        return this.id;
    }

    public String getOrgName(){
        return this.orgName;
    }

    public String getRepoName(){
        return this.repoName;
    }

    public void setOrgName(String orgName){
        this.orgName = orgName;
    }

    public void setRepoName(String repoName){
        this.repoName = repoName;
    }

    @Override
    public String toString(){
        return "GithubProject{" +
                "id="+ id +
                ", orgName ='" + orgName +'\''+
                ", repoName = '" + repoName + '\''+
                '}';
    }


}
