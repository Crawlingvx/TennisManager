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
 *
 * @author Administrador
 */
public class ABBJugadores implements Serializable
{
    private NodoJugadores raiz;

    public ABBJugadores(NodoJugadores raiz) 
    {
        this.raiz = raiz;
    }

    public NodoJugadores getRaiz() 
    {
        return raiz;
    }

    public void setRaiz(NodoJugadores raiz) 
    {
        this.raiz = raiz;
    }
    
    // ABB vacio cuando raiz es nula
    public boolean estaVacia(){
        return raiz == null;
    }
    
    // Recorrido Pre-Orden del ABB. Se usa, se va por la izquierda y luego por la derecha
    public void mostrarPreOrden(NodoJugadores aux)
    {
        if(aux != null)
        {
            aux.mostrar();
            mostrarPreOrden(aux.getHijoIzquierdo());
            mostrarPreOrden(aux.getHijoDerecho()); 
        }
    }
    
    // Recorrido En-Orden del ABB. Se va por la izquierda, se usa y luego por la derecha
    public ArrayList retornarEnOrden(NodoJugadores aux)
    {
        ArrayList array = new ArrayList();
        
        if(aux != null)
        {
            retornarEnOrden(aux.getHijoIzquierdo());
            array.add(aux);
            retornarEnOrden(aux.getHijoDerecho()); 
        }
        
        return array;
    }
    
    // Recorrido Post-Orden del ABB. Se va por la izquierda, luego por la derecha y se usa
    public void sobreescribirJugador(NodoJugadores aux, TextField text)
    {
        if(aux != null)
        {
            if( (text.getId().equals(aux.getNombre())) && (!text.getText().equals(aux.getNombre())) )
                aux.setNombre(text.getText());
            else
            {
                sobreescribirJugador(aux.getHijoIzquierdo(), text);
                sobreescribirJugador(aux.getHijoDerecho(), text);
            }
        }
    }
    
    public void insertarJugador(NodoJugadores aux, NodoJugadores nodJugador) throws Exception
    {
        if(estaVacia())
        {
            raiz = nodJugador;
        }
	else if(nodJugador.getCedula() < aux.getCedula())
	{
            if(aux.getHijoIzquierdo()== null)
		aux.setHijoIzquierdo(nodJugador);
            else
                insertarJugador(aux.getHijoIzquierdo(), nodJugador);
        }
	else if(nodJugador.getCedula() > aux.getCedula())
	{
            if(aux.getHijoDerecho()== null)
		aux.setHijoDerecho(nodJugador);
            else
		insertarJugador(aux.getHijoDerecho(), nodJugador);
        }
        else
        {
            throw new Exception("Jugador duplicado");
        }
    }
    
    static final long serialVersionUID = 8925409;
}
