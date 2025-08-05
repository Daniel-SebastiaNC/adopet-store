package br.com.alura.adopetstore.service;

import br.com.alura.adopetstore.email.EmailRelatorio;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

    private final RelatorioService relatorioService;
    private final EmailRelatorio emailRelatorio;

    public AgendamentoService(RelatorioService relatorioService, EmailRelatorio emailRelatorio) {
        this.relatorioService = relatorioService;
        this.emailRelatorio = emailRelatorio;
    }

    @Scheduled(cron = "0 59 19 * * *")
    public void enviarRelatorio() {
        var estoque = relatorioService.infoEstoque();
        var faturamento = relatorioService.faturamentoObtido();

        emailRelatorio.enviar(estoque, faturamento);

    }
}
