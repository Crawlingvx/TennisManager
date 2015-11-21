/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

/**
 *
 * @author Administrador
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
    
    // Metodo donde se inserta el nodo nuevo al final (ultimo) de la cola
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
    
    // Metodo donde se elimina el nodo que esta de primero en la cola
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
    
    // Metodo donde se obtiene los datos del primer nodo de la cola
    public String primeroCola()
    {
        return cabeza.getNombre();
    }

    public NodoJugadores getCabeza() 
    {
        return cabeza;
    }
    
    static final long serialVersionUID = 8925409;
}