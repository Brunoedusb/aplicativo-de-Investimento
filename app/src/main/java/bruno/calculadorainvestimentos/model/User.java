package bruno.calculadorainvestimentos.model;

/**
 * Created by UCL on 24/04/2018.
 */
public class User {

    /**
     * Property::Id
     * Description::Identificador do usuário
     **/

    /**
     * variable integerId
     * type Integer
     **/
    private Integer integerId;

    /**
     * Get and Set variable integerId
     * @return and @param type Integer
     **/
    public Integer getId () { return this.integerId;}
    public void setId(Integer newIntegerId){ this.integerId = newIntegerId ;}



    /**
     * Property::Name
     * Description::Nome do usuário
     **/

    /**
     * variable stringName
     * type String
     **/
    private String stringName;

    /**
     * Get and Set variable stringName
     * @return and @param type String
     **/
    public String getName () { return this.stringName;}
    public void setName(String newStringName){ this.stringName = newStringName ;}


    private Double ValorInicial;

    public Double getValorInicial() {
        return ValorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        ValorInicial = valorInicial;
    }

    private Double Aporte;

    private Double Taxa;

    private Integer Tempo;

    private Double Resultado;

    public Double getAporte() {
        return Aporte;
    }

    public void setAporte(Double aporte) {
        Aporte = aporte;
    }

    public Double getTaxa() {
        return Taxa;
    }

    public void setTaxa(Double taxa) {
        Taxa = taxa;
    }

    public Integer getTempo() {
        return Tempo;
    }

    public void setTempo(Integer tempo) {
        Tempo = tempo;
    }

    public Double getResultado() {
        return Resultado;
    }

    public void setResultado(Double resultado) {
        Resultado = resultado;
    }
}
