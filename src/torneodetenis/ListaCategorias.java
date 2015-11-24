/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 * Clase ListaAnos
 * Esta clase representa la lista de categorias que cada equipo tiene.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 1.04, 16/11/2015
 */
public class ListaCategorias implements Serializable
{
    private NodoCategorias _cabeza;
    private NodoCategorias _cola;

    public ListaCategorias(NodoCategorias cabeza) 
    {
        this._cabeza = cabeza;
    }

    public NodoCategorias getCabeza() 
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
    public void insertarPrimero(NodoCategorias nuevo) 
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
    * Metodo buscarCategorias
    * Metodo que determina si la categoria del jugador introducido por el usuario ya existe.
    * 
    * @param nodCategoria es el nodo con la categoria del jugador introducida por el usuario
    */
    public NodoCategorias buscarCategorias(NodoCategorias nodCategoria)
    {
        NodoCategorias aux = _cabeza;
        
        while(aux != null)
        {
            //retorna aux si la categoria del jugador ya esta registrada
            if( (nodCategoria.getNumCategoria() == aux.getNumCategoria()) && (nodCategoria.getTipoCategoria().equalsIgnoreCase(aux.getTipoCategoria())) )
            {
                return aux;
            }
            else
            {
                aux = aux.getProximo();
            }
        }
        
        return null; //retorna null si la categoria del jugador no esta registrada
    }
    
    static final long serialVersionUID = 8925409;
}