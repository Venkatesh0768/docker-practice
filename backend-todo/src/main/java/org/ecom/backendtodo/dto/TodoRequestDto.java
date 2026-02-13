package org.ecom.backendtodo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoRequestDto {
    private String title;
    private boolean completed;
    private UUID userId;
}
