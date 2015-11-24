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
public class NodoCategorias implements Serializable
{
    private int numCategoria;
    private String tipoCategoria;
    private NodoCategorias proximo;
    private ABBJugadores jugadores;

    public NodoCategorias(int numCategoria, String tipoCategoria)
    {
        this.numCategoria = numCategoria;
        this.tipoCategoria = tipoCategoria;
        this.proximo = null;
        this.jugadores = new ABBJugadores(null);
    }

    public int getNumCategoria()
    {
        return numCategoria;
    }

    public void setNumCategoria(int numCategoria)
    {
        this.numCategoria = numCategoria;
    }

    public String getTipoCategoria()
    {
        return tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria)
    {
        this.tipoCategoria = tipoCategoria;
    }

    public ABBJugadores getJugadores()
    {
        return jugadores;
    }

    public NodoCategorias getProximo() 
    {
        return proximo;
    }

    public void setProximo(NodoCategorias proximo)
    {
        this.proximo = proximo;
    }
    
    public void mostrar()
    {
        System.out.println("Tipo de equipo: " + numCategoria);
    }
    
    public void insertarABB(NodoJugadores jugador) throws Exception
    {
        NodoJugadores nodJugador = new NodoJugadores(jugador.getNombre(), jugador.getCedula(), jugador.getSexo(), jugador.getEdad(), jugador.getEquipo(), jugador.getCategoria(), 0, 0, 0, null, null);
        NodoJugadores aux = jugadores.getRaiz();
        System.out.println("Jugador insertado: " + nodJugador.getNombre());
        jugadores.insertarJugador(aux, nodJugador);
    }
    
    static final long serialVersionUID = 8925409;
}
