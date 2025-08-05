package br.com.alura.adopetstore.email;

import br.com.alura.adopetstore.dto.RelatorioEstoque;
import br.com.alura.adopetstore.dto.RelatorioFaturamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailRelatorio {
    @Autowired
    private EnviadorEmail enviador;

    public void enviar(RelatorioEstoque estoque, RelatorioFaturamento faturamento) {
        enviador.enviarEmail(
                "Relatório gerado",
                "admin@email.com.br",
                """
                        Olá! 
                        Segue seus relatórios:
                        
                        %s
                        
                        %s
                        """.formatted(estoque, faturamento));
    }
}
