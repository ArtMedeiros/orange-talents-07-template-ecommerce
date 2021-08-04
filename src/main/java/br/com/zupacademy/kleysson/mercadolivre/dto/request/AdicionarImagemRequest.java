package br.com.zupacademy.kleysson.mercadolivre.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class AdicionarImagemRequest {

    @NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AdicionarImagemRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }
}
