package br.com.zupacademy.kleysson.mercadolivre.config.email;

import br.com.zupacademy.kleysson.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {

    private final EmailSender emailSender;

    public Email(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void novaPergunta(Pergunta pergunta) {
        String mensagem = "Nova pergunta - "+pergunta.getProduto().getNome();

        emailSender.send(pergunta.getInteressado().getUsername(), pergunta.getTitulo(), mensagem, pergunta.getDonoProduto().getUsername());
    }
}
