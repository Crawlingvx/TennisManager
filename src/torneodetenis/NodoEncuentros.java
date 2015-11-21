/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class NodoEncuentros implements Serializable
{
    private String semana;
    private String tipo;
    private NodoEncuentros proximo;
    private NodoEncuentros anterior;

    public NodoEncuentros(String semana, String tipo, NodoEncuentros proximo, NodoEncuentros anterior) 
    {
        this.semana = semana;
        this.tipo = tipo;
        this.proximo = proximo;
        this.anterior = anterior;
    }

    public String getSemana() 
    {
        return semana;
    }

    public void setSemana(String semana) 
    {
        this.semana = semana;
    }

    public String getTipo() 
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public NodoEncuentros getProximo()
    {
        return proximo;
    }

    public void setProximo(NodoEncuentros proximo) 
    {
        this.proximo = proximo;
    }

    public NodoEncuentros getAnterior() 
    {
        return anterior;
    }

    public void setAnterior(NodoEncuentros anterior) 
    {
        this.anterior = anterior;
    }
    
    public void mostrar()
    {
        System.out.println("Semana: " + semana + " - Tipo de Encuentro: " + tipo);
    }
}
