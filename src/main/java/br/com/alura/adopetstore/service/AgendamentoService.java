package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.email.EmailRelatorio;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AgendamentoService {

    private final RelatorioService relatorioService;
    private final EmailRelatorio emailRelatorio;

    public AgendamentoService(RelatorioService relatorioService, EmailRelatorio emailRelatorio) {
        this.relatorioService = relatorioService;
        this.emailRelatorio = emailRelatorio;
    }

    @Scheduled(cron = "0 13 20 * * *")
    public void enviarRelatorio() {
        var estoque = relatorioService.infoEstoque();
        var faturamento = relatorioService.faturamentoObtido();

        // Garantia que os 2 vão funcionar juntos
        CompletableFuture.allOf(estoque, faturamento).join();

        try {
            emailRelatorio.enviar(estoque.get(), faturamento.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
