import java.util.ArrayList;
import java.util.List;

public class Ficha {
    private Paciente paciente;
    private List<String> sintomas; 
    private List<String> datasConsultas; 
    private List<AnotacaoConsulta> anotacoesConsultas; 

    // Construtor
    public Ficha(Paciente paciente) {
        this.paciente = paciente;
        this.sintomas = new ArrayList<>();
        this.datasConsultas = new ArrayList<>();
        this.anotacoesConsultas = new ArrayList<>();
    }

    
    public void adicionarSintoma(String sintoma) {
        sintomas.add(sintoma);
    }

    public void adicionarConsulta(String dataConsulta, String anotacao) {
        datasConsultas.add(dataConsulta);
        anotacoesConsultas.add(new AnotacaoConsulta(dataConsulta, anotacao));
    }

   
    public Paciente getPaciente() {
        return paciente;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public List<String> getDatasConsultas() {
        return datasConsultas;
    }

    public List<AnotacaoConsulta> getAnotacoesConsultas() {
        return anotacoesConsultas;
    }


    public class AnotacaoConsulta {
        private String dataConsulta;
        private String anotacao;

       
        public AnotacaoConsulta(String dataConsulta, String anotacao) {
            this.dataConsulta = dataConsulta;
            this.anotacao = anotacao;
        }

        public String getDataConsulta() {
            return dataConsulta;
        }

        public String getAnotacao() {
            return anotacao;
        }

    }
}
