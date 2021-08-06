package br.com.zupacademy.kleysson.mercadolivre.utils.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmailSenderServiceFake implements EmailSender{
    @Override
    public void send(String remetente, String mensagem, String titulo, String destinatario) {
        System.out.println("Email [\n\tremetente: "+remetente+"");
        System.out.println("\tdestinatario: "+destinatario);
        System.out.println("\ttitulo: "+titulo);
        System.out.println("\tmensagem: "+mensagem+"\n]");
    }
}
