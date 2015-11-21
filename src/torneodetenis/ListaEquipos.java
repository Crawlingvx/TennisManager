/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class ListaEquipos implements Serializable, Cloneable
{
    private NodoEquipos cabeza;

    public ListaEquipos() 
    {
        this.cabeza = null;
    }

    public NodoEquipos getCabeza() 
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
    public void insertarPrimero(NodoEquipos nuevo) 
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
    public NodoEquipos eliminarPrimero() 
    {
        NodoEquipos nodoEliminado = null;
        
        if (!estaVacia()) 
        {
            nodoEliminado = cabeza;
            cabeza = cabeza.getProximo();
            nodoEliminado.setProximo(null);
        } 
        
        return nodoEliminado;
    }
    
    /**
    * Metodo buscarAnos
    * Metodo que determina si el equipo del jugador que se esta introduciendo ya existe en el torneo
    * 
    * @param nodEquipo es el nodo con el equipo del jugador introducido por el usuario.
    */
    public NodoEquipos buscarEquipos(NodoEquipos nodEquipo)
    {
        NodoEquipos aux = cabeza;
        
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
    
    public List<String> retornarArrayUniversidad()
    {
        List<String> choices = new ArrayList<>();
        NodoEquipos aux;
        aux = cabeza;
        
        while(aux != null)
        {
            choices.add(aux.getUniversidad());
            aux = aux.getProximo();
        }
        
        return choices;
    }
    
    //Metodo que cuenta los nodos de la lista
    public int contar()
    {
        NodoEquipos aux = cabeza;
        int cont=0;
        
        while(aux != null)
        {
            aux = aux.getProximo();
            cont++;
        }
        return cont;
    }
    
    static final long serialVersionUID = 8925409;
}
