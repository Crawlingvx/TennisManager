/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 * Clase NodoAnos
 * Esta clase representa los nodos individuales de la lista de años.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 2.00, 16/11/2015
 */
public class NodoAnos implements Serializable
{
    private int _ano;
    private NodoAnos _proximo;
    private NodoAnos _anterior;
    private ListaEquipos _equipos;

    public NodoAnos(int año) 
    {
        this._ano = año;
        this._proximo = null;
        this._equipos = new ListaEquipos();
    }

    public int getAno() 
    {
        return _ano;
    }

    public void setAno(int año) 
    {
        this._ano = año;
    }

    public ListaEquipos getEquipos()
    {
        return _equipos;
    }

    public NodoAnos getProximo() 
    {
        return _proximo;
    }

    public void setProximo(NodoAnos proximo) 
    {
        this._proximo = proximo;
    }

    public NodoAnos getAnterior()
    {
        return _anterior;
    }

    public void setAnterior(NodoAnos anterior)
    {
        this._anterior = anterior;
    }
    
    /**
    * Metodo sortEquipo
    * Metodo que inserta al jugador en su equipo especificado.
    * 
    * @param jugador el jugador desencolado al crear el torneo
    */
    public void sortEquipo(NodoJugadores jugador) throws Exception
    {
        NodoEquipos nodEquipo = new NodoEquipos(jugador.getEquipo(), 0, 0); //NodoEquipos que contiene el equipo al que el jugador pertenece
        NodoEquipos aux = null;
        
        if(_equipos.estaVacia()) //condicion en caso de que no haya equipos registrados
        {
            _equipos.insertarPrimero(nodEquipo);
            nodEquipo.sortCategoria(jugador); //inserta al jugador en su equipo
        }
        else
        {
            aux = _equipos.buscarEquipos(nodEquipo); //devuelve si el equipo del jugador esta registrado o no
            
            if(aux != null) //condicion en caso de que el equipo del jugador este registrado
            {
                aux.sortCategoria(jugador); //inserta al jugador en su equipo
            }
            else //condicion en caso de que hayan equipos registrados, pero el equipo del jugador no sea uno de ellos
            {
                _equipos.insertarPrimero(nodEquipo); 
                nodEquipo.sortCategoria(jugador); //inserta al jugador en su equipo
            }
        }
    }
    
    static final long serialVersionUID = 8925409;
}
