package by.bysend.contractor.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TokenDTO {
    private String access;
    private String refresh;
}
