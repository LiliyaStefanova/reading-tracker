package org.elstere.booktrkr.api.entities.inbound;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleClientVolumeInfo {

    String title;

    String subtitle;

    List<String> authors;

    String publisher;

    String description;

    String printType;

    List<String> categories;

    String language;
}
