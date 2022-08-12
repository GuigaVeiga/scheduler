package jobemail.service;

import org.junit.jupiter.api.Test;
import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JobServiceTest {

    @Test
    void cron() {
        var expression = CronExpression.parse("10 * * * * *");
        var result = expression.next(LocalDateTime.now());
        System.out.println(result);
    }

    @Test
    void buscarAno() {
        String mesFaturamento = LocalDate.now().getMonth().minus(7)
                .getDisplayName(TextStyle.SHORT, new Locale("pt"));

        System.out.println("Iniciando JOB");
        System.out.println("Mes de faturamento: " + mesFaturamento);

        if(mesFaturamento.equals("dez") || mesFaturamento.equals("DEZ")) {
            System.out.println("Ano de faturamento 1: " + (LocalDate.now().getYear() - 1));
        } else {
            System.out.println("Ano de faturamento 2: " + LocalDate.now().getYear());
        }
    }

    @Test
    void test() {
        String corte = "15D";
        Integer tipoPeriodo = Integer.valueOf(corte.substring(0, corte.length() - 1));
        System.out.println("Quantidade de dias: " + tipoPeriodo);


        if (corte.substring(corte.length() - 1).equals("D")) {
            buscarPeriodoDiario(getPeriodo(corte));
        }

        if (corte.substring(corte.length() - 1).equals("M")) {
            buscarPeriodoMensal(getPeriodo(corte));
        }

        System.out.println("Tamanho (length): " + corte.length());
        System.out.println("Dia ou Mês: " + corte.substring(corte.length() - 1));
    }

    Integer getPeriodo(String corte) {
        try {
            return Integer.valueOf(corte.substring(0, corte.length() - 1));
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }
    
    void buscarPeriodoDiario(Integer corte) {
        LocalDate primeiroDia;
        LocalDate ultimoDia;

        primeiroDia = LocalDate.now().minusDays(corte);
        ultimoDia = LocalDate.now();

        System.out.println("Pegando dias de corte");
        System.out.println("Primeiro dia: " + primeiroDia);
        System.out.println("Ultimo dia: " + ultimoDia);
    }

    void buscarPeriodoMensal(Integer corte) {
        LocalDate primeiroDia;
        LocalDate ultimoDia;

        if (corte == 1) {
            primeiroDia = LocalDate.now().minusMonths(1).withDayOfMonth(corte);
            ultimoDia = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

            System.out.println("Pegando Mês inteiro");
            System.out.println("Primeiro dia: " + primeiroDia);
            System.out.println("Ultimo dia: " + ultimoDia);
        } else {
            primeiroDia = LocalDate.now().minusMonths(1).withDayOfMonth(corte);
            ultimoDia = primeiroDia.plusMonths(1).withDayOfMonth(corte - 1);

            System.out.println("Pegando Mês com data de corte");
            System.out.println("Primeiro dia: " + primeiroDia);
            System.out.println("Ultimo dia: " + ultimoDia);
        }
    }

}