/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 * Clase NodoEquipos
 * Esta clase representa los nodos individuales de la lista de equipos.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 2.00, 16/11/2015
 */
public class NodoCategorias implements Serializable
{
    private int _numCategoria;
    private String _tipoCategoria;
    private NodoCategorias _proximo;
    private NodoCategorias _anterior;
    private ABBJugadores _jugadores;

    public NodoCategorias(int numCategoria, String tipoCategoria)
    {
        this._numCategoria = numCategoria;
        this._tipoCategoria = tipoCategoria;
        this._proximo = null;
        this._jugadores = new ABBJugadores(null);
    }

    public int getNumCategoria()
    {
        return _numCategoria;
    }

    public void setNumCategoria(int numCategoria)
    {
        this._numCategoria = numCategoria;
    }

    public String getTipoCategoria()
    {
        return _tipoCategoria;
    }

    public void setTipoCategoria(String tipoCategoria)
    {
        this._tipoCategoria = tipoCategoria;
    }

    public ABBJugadores getJugadores()
    {
        return _jugadores;
    }

    public NodoCategorias getProximo() 
    {
        return _proximo;
    }

    public void setProximo(NodoCategorias proximo)
    {
        this._proximo = proximo;
    }

    public NodoCategorias getAnterior()
    {
        return _anterior;
    }

    public void setAnterior(NodoCategorias anterior)
    {
        this._anterior = anterior;
    }
    
    /**
    * Metodo sortEquipo
    * Metodo que crea un nuevo jugador para insertarlo en el arbol binario de busqueda de su categoria.
    * 
    * @param jugador el jugador desencolado al crear el torneo
    */
    public void insertarABB(NodoJugadores jugador) throws Exception
    {
        //crea un nodo nuevo para el jugador, reemplazando los atributos de la cola (proximo), con los del ABB (hijoIzquierdo, hijoDerecho)
        NodoJugadores nodJugador = new NodoJugadores(jugador.getNombre(), jugador.getCedula(), jugador.getSexo(), jugador.getEdad(), jugador.getEquipo(), jugador.getCategoria(), 0, 0, 0, null, null);
        
        NodoJugadores aux = _jugadores.getRaiz(); //obtiene la raiz del arbol binario de busqueda
        _jugadores.ubicarJugador(aux, nodJugador); //inserta al jugador en el arbol binario de busqueda
    }
    
    static final long serialVersionUID = 8925409;
}
