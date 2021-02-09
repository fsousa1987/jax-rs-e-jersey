package com.francisco.loja.dao;

import com.francisco.loja.modelo.Projeto;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
public class ProjetoDAO {

    private static Map<Long, Projeto> banco = new HashMap<>();
    private static AtomicLong contador = new AtomicLong(1);

    static {
        banco.put(1L, new Projeto(1L, "Minha loja", 2014));
        banco.put(2L, new Projeto(2L, "Alura", 2012));
    }

    public void adiciona(Projeto projeto) {
        long id = contador.incrementAndGet();
        projeto.setId(id);
        banco.put(id, projeto);
    }

    public Projeto busca(Long id) {
        return banco.get(id);
    }

    public Projeto remove(long id) {
        return banco.remove(id);
    }
}
