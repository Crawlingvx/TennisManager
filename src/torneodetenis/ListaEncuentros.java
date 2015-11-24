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
public class ListaEncuentros implements Serializable
{
    private NodoEncuentros cabeza;
    private NodoEncuentros cola;

    public ListaEncuentros(NodoEncuentros cabeza, NodoEncuentros cola) 
    {
        this.cabeza = cabeza;
        this.cola = cola;
    }

    public NodoEncuentros getCabeza()
    {
        return cabeza;
    }

    public NodoEncuentros getCola()
    {
        return cola;
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
    public void insertarPrimero(NodoEncuentros nuevo) 
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
    
    static final long serialVersionUID = 8925409;
}
