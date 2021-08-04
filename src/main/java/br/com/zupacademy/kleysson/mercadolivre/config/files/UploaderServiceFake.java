package br.com.zupacademy.kleysson.mercadolivre.config.files;

import io.jsonwebtoken.lang.Assert;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Profile("dev")
public class UploaderServiceFake implements Uploader{

    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        Assert.notEmpty(imagens, "A lista de imagens não pode estar vazia");

        return imagens.stream().map(img -> "https://uploadfile.com/file/"+img.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}