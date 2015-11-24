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
public class NodoEquipos implements Serializable
{
    private String _universidad;
    private int _puntos;
    private int _ranking;
    private NodoEquipos _proximo;
    private NodoEquipos _anterior;
    private ListaCategorias _categorias;

    public NodoEquipos(String universidad, int puntos, int ranking)
    {
        this._universidad = universidad;
        this._puntos = puntos;
        this._ranking = ranking;
        this._proximo = null;
        this._anterior = null;
        this._categorias = new ListaCategorias(null);
    }

    public String getUniversidad()
    {
        return _universidad;
    }

    public void setUniversidad(String universidad) 
    {
        this._universidad = universidad;
    }

    public int getPuntos() 
    {
        return _puntos;
    }

    public void setPuntos(int puntos) 
    {
        this._puntos = puntos;
    }

    public ListaCategorias getCategorias()
    {
        return _categorias;
    }

    public int getRanking()
    {
        return _ranking;
    }

    public void setRanking(int ranking)
    {
        this._ranking = ranking;
    }

    public NodoEquipos getProximo()
    {
        return _proximo;
    }

    public void setProximo(NodoEquipos proximo)
    {
        this._proximo = proximo;
    }

    public NodoEquipos getAnterior()
    {
        return _anterior;
    }

    public void setAnterior(NodoEquipos anterior)
    {
        this._anterior = anterior;
    }
    
    /**
    * Metodo sortCategoria
    * Metodo que inserta al jugador en su categoria especificada.
    * 
    * @param jugador el jugador desencolado al crear el torneo
    */
    public void sortCategoria(NodoJugadores jugador) throws Exception
    {
        NodoCategorias nodCategoria = new NodoCategorias(jugador.getCategoria(), jugador.getSexo()); //NodoCategorias que contiene la categoria a la que el jugador pertenece
        NodoCategorias aux = null;
        
        if(_categorias.estaVacia()) //condicion en caso de que no haya categorias registradas
        {
            _categorias.insertarPrimero(nodCategoria);
            nodCategoria.insertarABB(jugador); //inserta al jugador en su categoria
        }
        else
        {
            aux = _categorias.buscarCategorias(nodCategoria);
            
            if(aux != null) //condicion en caso de que la categoria del jugador ya este registrada
            {
                aux.insertarABB(jugador); //inserta al jugador en su categoria
            }
            else //condicion en caso de que hayan categorias registradas, pero la categoria del jugador no sea una de ellas
            {
                _categorias.insertarPrimero(nodCategoria);
                nodCategoria.insertarABB(jugador); //inserta al jugador en su categoria
            }
        }
    }
    
    static final long serialVersionUID = 8925409;
}
