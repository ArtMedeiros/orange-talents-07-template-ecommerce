package br.com.zupacademy.kleysson.mercadolivre.model;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListaOpinioes {

    private Set<Opiniao> opinioes;

    public ListaOpinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> mapeador) {
        return this.opinioes.stream().map(mapeador)
                .collect(Collectors.toSet());
    }

    public Double media() {
        Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble media = notas.stream().mapToDouble(nota -> nota).average();
        return media.orElse(0.0);
    }

    public Integer total() {
        return this.opinioes.size();
    }
}
