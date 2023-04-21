package backend.datahora;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RegistroDataHora {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public double calcularHorasExtras(String dataHoraInicio, String dataHoraFim){
        if (validarData(dataHoraInicio) && validarData(dataHoraFim)){
            if (verificarSequenciaDatas(dataHoraInicio, dataHoraFim)){
                int minutos = pegarDiferencaMinutos(dataHoraInicio, dataHoraFim);
                // minutos dividido por 60 (quantidade de minutos me 1 hora) vezes o valor (em reais) da hora
                return minutos;
            }
        }
        return 0;
    }

    public boolean validarData(String data){
        String dateFormat = "dd/MM/uuuu HH:mm";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate.parse(data, dateTimeFormatter);
            return true;
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }

    public int pegarDiferencaMinutos(String dtInicio, String dtFim){
        try {
            // obtém a diferença entre duas datas em minutos
            long elapsedms = simpleDateFormat.parse(dtFim).getTime() - simpleDateFormat.parse(dtInicio).getTime();
            long diferencaMinutos = TimeUnit.MINUTES.convert(elapsedms, TimeUnit.MILLISECONDS);
            return (int) diferencaMinutos;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean verificarSequenciaDatas(String dataInicio, String dataFim){
        try {
            Date dataInicioFormatada = simpleDateFormat.parse(dataInicio);
            Date dataFimFormatadata = simpleDateFormat.parse(dataFim);

            if (dataFimFormatadata.compareTo(dataInicioFormatada) > 0 && new Date().compareTo(dataFimFormatadata) > 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(ParseException e){
            return false;
        }
    }
}
