package com.elster.booktracker;

import ru.vyarus.dropwizard.guice.module.support.DropwizardAwareModule;

public class DropwizardModule extends DropwizardAwareModule<BookTrackerConfiguration> {
    protected void configure() {
        bootstrap();
        environment();
        configuration();
        appPackage();
    }
}
