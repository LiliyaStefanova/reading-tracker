package org.elstere.reading.tracker.github;

import org.elstere.reading.tracker.dao.GithubProject;

import java.util.List;

public class DashboardEntry {

    private final GithubProject project;

    private final List<RepositoryEvent> events;

    public DashboardEntry(GithubProject project, List<RepositoryEvent> events){
        this.project = project;
        this.events = events;
    }

    public GithubProject getProject(){
        return project;
    }

    public List<RepositoryEvent> getEvents(){
        return events;
    }
}
