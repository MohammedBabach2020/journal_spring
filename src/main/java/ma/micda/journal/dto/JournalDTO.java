package ma.micda.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalDTO {

    private Long id;

    private String title;

    private String text;

    private Long UserId;

}
