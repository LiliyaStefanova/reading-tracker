package org.elstere.booktrkr.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GithubProjectRepository extends PagingAndSortingRepository<GithubProject, Long> {

    //auto generated method
     GithubProject findByRepoName(String repoName);
}
