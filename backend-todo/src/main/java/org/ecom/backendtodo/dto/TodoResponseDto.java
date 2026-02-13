package org.ecom.backendtodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoResponseDto {
    private UUID id;
    private String title;
    private boolean completed;
    private UUID userId;
    private String username;
}
