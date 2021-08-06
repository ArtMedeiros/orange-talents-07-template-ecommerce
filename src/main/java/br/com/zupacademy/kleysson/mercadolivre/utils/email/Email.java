package br.com.zupacademy.kleysson.mercadolivre.utils.email;

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

    public void erroPagamento(Compra compra, String linkPagamento) {
        String tituloEmail = "Falha no pagamento";
        String mensagem = "Não foi possível realizar o pagamento do produto "+compra.getProduto().getNome()+
                ". Por favor tente novamente através do link abaixo:\n"+linkPagamento;

        emailSender.send(compra.getDonoProduto().getUsername(), mensagem, tituloEmail, compra.getComprador().getUsername());
    }

    public void sucessoPagamento(Compra compra) {
        String tituloEmail = "Pagamento realizado com sucesso";
        String mensagem = "\n\t\t'A compra do produto foi realizada com sucesso"+
                "\n\t\tStatus da compra: "+compra.getStatus()+
                "\n\t\tProduto: "+compra.getProduto().getNome()+
                "\n\t\tQuantidade: "+compra.getQuantidade()+
                "\n\t\tValor pago: "+compra.getValorCompra()+
                "\n\t\tPagamento via: "+compra.getPagamento()+"'";

        emailSender.send(compra.getDonoProduto().getUsername(), mensagem, tituloEmail, compra.getComprador().getUsername());
    }
}
