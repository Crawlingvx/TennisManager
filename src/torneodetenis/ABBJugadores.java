/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Clase ABBJugadores
 * Esta clase representa el Arbol Binario de Busqueda que contiene a los jugadores de cada equipo y de cada categoria que participaron en el torneo.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 3.00, 23/11/2015
 */
public class ABBJugadores implements Serializable
{
    private NodoJugadores _raiz;

    public ABBJugadores(NodoJugadores raiz) 
    {
        this._raiz = raiz;
    }

    public NodoJugadores getRaiz() 
    {
        return _raiz;
    }

    public void setRaiz(NodoJugadores raiz) 
    {
        this._raiz = raiz;
    }
    
    // ABB vacio cuando raiz es nula
    public boolean estaVacia(){
        return _raiz == null;
    }
    
    /**
    * Metodo retornarEnOrden
    * Metodo que retorna un array con los jugadores del ABB con el metodo de EnOrden.
    * 
    * @param aux representa los nodos del ABB
    * 
    * @return array ArrayList con los jugadores del ABB
    */
    public ArrayList retornarEnOrden(NodoJugadores aux)
    {
        ArrayList<NodoJugadores> array = new ArrayList();
        
        if(aux != null)
        {
            retornarEnOrden(aux.getHijoIzquierdo());
            array.add(aux);
            retornarEnOrden(aux.getHijoDerecho()); 
        }
        
        return array;
    }
    
    /**
    * Metodo sobreescribirJugador
    * Metodo sobreescribe al jugador modificado.
    * 
    * @param aux representa los nodos del ABB
    * @param text el TextField que contiene el nombre del jugador
    */
    public void sobreescribirJugador(NodoJugadores aux, TextField text)
    {
        if(aux != null)
        {
            if( (text.getId().equals(aux.getNombre())) && (!text.getText().equals(aux.getNombre())) ) //condicion para determinar si se ha modificado el jugador
            {
                NodoJugadores jugador = new NodoJugadores(text.getText(), 0, aux.getSexo(), 0, aux.getEquipo(), aux.getCategoria(), 0, 0, 0, aux.getHijoIzquierdo(), aux.getHijoDerecho());
                aux = jugador;
            }
            else
            {
                sobreescribirJugador(aux.getHijoIzquierdo(), text);
                sobreescribirJugador(aux.getHijoDerecho(), text);
            }
        }
    }
    
    /**
    * Metodo InsertarJugador
    * Metodo que ubica al jugador en el arbol binario de busqueda.
    * 
    * @param jugador el jugador desencolado al crear el torneo
    */
    public void ubicarJugador(NodoJugadores aux, NodoJugadores jugador) throws Exception
    {
        if(estaVacia())
        {
            _raiz = jugador; //convierte al jugador en la raiz en caso de que no hayan nodos en el ABB
        }
	else if(jugador.getCedula() < aux.getCedula()) //ubica al jugador a la izquierda del nodo actual
	{
            if(aux.getHijoIzquierdo()== null)
		aux.setHijoIzquierdo(jugador); //inserta al jugador si el nodo a la izquierda del nodo actual es null
            else
                ubicarJugador(aux.getHijoIzquierdo(), jugador);
        }
	else if(jugador.getCedula() > aux.getCedula()) //inserta el jugador a la derecha del nodo actual
	{
            if(aux.getHijoDerecho()== null)
		aux.setHijoDerecho(jugador); //inserta al jugador si el nodo a la derecha del nodo actual es null
            else
		ubicarJugador(aux.getHijoDerecho(), jugador);
        }
        else
        {
            throw new Exception("Jugador duplicado");
        }
    }
    
    static final long serialVersionUID = 8925409;
}
