package br.com.zupacademy.kleysson.mercadolivre.config.email;

import br.com.zupacademy.kleysson.mercadolivre.model.Compra;
import br.com.zupacademy.kleysson.mercadolivre.model.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class Email {

    private final EmailSender emailSender;

    public Email(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void novaPergunta(Pergunta pergunta) {
        String tituloEmail = "Nova pergunta - "+pergunta.getProduto().getNome();

        emailSender.send(pergunta.getInteressado().getUsername(), pergunta.getTitulo(), tituloEmail, pergunta.getDonoProduto().getUsername());
    }

    public void novaCompra(Compra compra) {
        String tituloEmail = "Nova compra - "+compra.getProduto().getNome();
        String mensagem = "Uma nova compra de "+compra.getQuantidade()+" unidades do produto "+compra.getProduto().getNome();

        emailSender.send(compra.getComprador().getUsername(), mensagem, tituloEmail, compra.getDonoProduto().getUsername());
    }
}
