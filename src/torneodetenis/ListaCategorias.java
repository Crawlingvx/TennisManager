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
public class ListaCategorias implements Serializable
{
    private NodoCategorias cabeza;

    public ListaCategorias(NodoCategorias cabeza) 
    {
        this.cabeza = cabeza;
    }

    public NodoCategorias getCabeza() 
    {
        return cabeza;
    }
    
    /**
    * Metodo estaVacia
    * Metodo que valida si la lista esta vacia.
    * 
    */
    public boolean estaVacia() 
    {
        return cabeza == null; 
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
            cabeza = nuevo;
        } 
        else 
        {
            nuevo.setProximo(cabeza);
            cabeza = nuevo;
        }
    }
    
    /**
    * Metodo eliminarPrimero
    * Metodo que elimina el primer nodo de la lista.
    * 
    */
    public NodoCategorias eliminarPrimero() 
    {
        NodoCategorias nodoEliminado = null;
        
        if (!estaVacia()) 
        {
            nodoEliminado = cabeza;
            cabeza = cabeza.getProximo();
            nodoEliminado.setProximo(null);
        } 
        
        return nodoEliminado;
    }
    
    /**
    * Metodo buscarCategorias
    * Metodo que determina si la categoria del jugador introducido por el usuario ya existe.
    * 
    * @param nodCategoria es el nodo con la categoria del jugador introducida por el usuario
    */
    public NodoCategorias buscarCategorias(NodoCategorias nodCategoria)
    {
        NodoCategorias aux = cabeza;
        
        while(aux != null)
        {
            //si la categoria esta registrada, retorna null
            if( (nodCategoria.getNumCategoria() == aux.getNumCategoria()) && (nodCategoria.getTipoCategoria().equalsIgnoreCase(aux.getTipoCategoria())) )
            {
                return aux;
            }
            else
            {
                aux = aux.getProximo();
            }
        }
        
        return null;
    }
    
    static final long serialVersionUID = 8925409;
}
