package org.elstere.booktrkr.dao.repository;

import org.elstere.booktrkr.dao.GithubProject;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface GithubProjectRepository extends PagingAndSortingRepository<GithubProject, UUID> {

    //auto generated method
     GithubProject findByRepoName(String repoName);
}
