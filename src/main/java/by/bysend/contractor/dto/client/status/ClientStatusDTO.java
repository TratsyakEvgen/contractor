package by.bysend.contractor.dto.client.status;

import by.bysend.contractor.model.entity.name.ClientStatusName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientStatusDTO {
    private long clientStatusId;
    private ClientStatusName status;
}
