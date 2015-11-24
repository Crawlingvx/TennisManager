/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 * Clase ListaAnos
 * Esta clase representa la lista de equipos que participaron en el torneo.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 3.01, 23/11/2015
 */
public class ListaEquipos implements Serializable, Cloneable
{
    private NodoEquipos _cabeza;
    private NodoEquipos _cola;

    public ListaEquipos() 
    {
        this._cabeza = null;
    }

    public NodoEquipos getCabeza() 
    {
        return _cabeza;
    }
    
    /**
    * Metodo estaVacia
    * Metodo que valida si la lista esta vacia.
    * 
    */
    public boolean estaVacia() 
    {
        return _cabeza == null; 
    }

    
    /**
    * Metodo insertarPrimero
    * Metodo que inserta un nodo de primero en la lista.
    * 
    */
    public void insertarPrimero(NodoEquipos nuevo) 
    {
        if (estaVacia()) 
        {
            _cabeza = _cola = nuevo;
        } 
        else 
        {
            if(_cabeza == _cola){
                nuevo.setProximo(_cabeza);
                _cola = _cabeza;
                _cabeza = nuevo;
                _cola.setAnterior(_cabeza);
            }
            else
            {
                nuevo.setProximo(_cabeza);
                _cabeza.setAnterior(nuevo);
                _cabeza = nuevo;
            }   
        }
    }
    
    /**
    * Metodo eliminarPrimero
    * Metodo que elimina el primer nodo de la lista.
    * 
    */
    public void eliminarEquipo(NodoEquipos equipoEliminado) 
    {
        NodoEquipos nodoAnterior = equipoEliminado.getAnterior();
        NodoEquipos nodoProximo = equipoEliminado.getProximo();
        
        if(!estaVacia())
        {
            if(_cabeza == _cola)
                _cabeza = _cola = null;
            else if(equipoEliminado == _cabeza)
            {
                nodoProximo.setAnterior(nodoAnterior);
                _cabeza = nodoProximo;
            }
            else if(equipoEliminado == _cola)
            {
                nodoAnterior.setProximo(nodoProximo);
                _cola = nodoAnterior;
            }
            else
            {
                nodoAnterior.setProximo(nodoProximo);
                nodoProximo.setAnterior(nodoAnterior);
            }
        }
    }
    
    /**
    * Metodo buscarEquipos
    * Metodo que determina si el equipo del jugador que se esta introduciendo ya existe en el torneo
    * 
    * @param nodEquipo es el nodo con el equipo del jugador introducido por el usuario.
    */
    public NodoEquipos buscarEquipos(NodoEquipos nodEquipo)
    {
        NodoEquipos aux = _cabeza;
        
        while(aux != null)
        {
            if( nodEquipo.getUniversidad().equals(aux.getUniversidad()) ) //retorna aux si el equipo ya esta registrado
            {
                return aux;
            }
            else
            {
                aux = aux.getProximo();
            }
        }
        
        return null; //retorna null si el equipo no esta registrado
    }
    
    static final long serialVersionUID = 8925409;
}
