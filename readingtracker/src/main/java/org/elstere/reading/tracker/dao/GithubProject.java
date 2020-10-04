package org.elstere.reading.tracker.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class GithubProject extends EntityWithUUID implements Serializable {

    private String orgName;

    @Column(unique=true)
    private String repoName;

    @Override
    public String toString(){
        return "GithubProject{" +
                "id="+ super.getId() +
                ", orgName ='" + orgName +'\''+
                ", repoName = '" + repoName + '\''+
                '}';
    }


}
