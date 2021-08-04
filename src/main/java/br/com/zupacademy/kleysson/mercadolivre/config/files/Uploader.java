package br.com.zupacademy.kleysson.mercadolivre.config.files;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface Uploader {

    Set<String> enviar(List<MultipartFile> imagens);
}
