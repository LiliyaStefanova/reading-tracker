package com.elster.booktracker.resources.definitions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookEnriched {

        private long id;

        @NotNull
        private String title;

        @NotNull
        private String author;

        @NotNull
        private String category;

        @NotNull
        private String genre;

        private String notes;

        @NotNull
        private String status;

        private boolean favourite;

}
