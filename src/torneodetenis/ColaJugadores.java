/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

/**
 * Clase ColaJugadores
 * Esta clase representa la cola de jugadores a ser llenada/vaciada para la creacion del torneo.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 1.00, 10/11/2015
 */
public class ColaJugadores
{
    private NodoJugadores cabeza;

    public ColaJugadores(NodoJugadores cabeza) 
    {
        this.cabeza = cabeza;
    }
    
    public boolean estaVacia() 
    {
        return cabeza == null;
    }
    
    /**
    * Metodo encolar
    * Metodo que añade un jugador al final de la cola.
    * 
    */
    public void encolar(NodoJugadores nuevo)
    {
        if (estaVacia()) 
            cabeza = nuevo;
        else 
        {
            NodoJugadores aux = cabeza;
            
            while(aux.getProximo()!= null)
            {
                aux = aux.getProximo();
            }
            
            aux.setProximo(nuevo);
        }
    }
    
    /**
    * Metodo desencolar
    * Metodo que elimina el jugador que se encuentre de primero en la cola. Este sera añadido al torneo.
    * 
    */
    public NodoJugadores desencolar()
    {
        NodoJugadores nodoEliminado = null;
        
        if (!estaVacia()) 
        {
            nodoEliminado = cabeza;
            cabeza = cabeza.getProximo();
            
            nodoEliminado.setProximo(null);
        } 
        
        return nodoEliminado;
    }

    public NodoJugadores getCabeza() 
    {
        return cabeza;
    }
    
    static final long serialVersionUID = 8925409;
}