/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.io.Serializable;

/**
 * Clase ListaAnos
 * Esta representa la lista de anos donde se crearon torneos. Contiene toda la informacion referente a los torneos en su interior.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 1.04, 16/11/2015
 */
public class ListaAnos implements Serializable
{
    private NodoAnos cabeza;
    private ListaEncuentros encuentros; //lista de Encuentros
    
    public ListaAnos() 
    {
        this.cabeza = null;
    }
    
    public ListaAnos(NodoAnos cabeza)
    {
        this.cabeza = cabeza;
    }

    public NodoAnos getCabeza() {
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
    public void insertarPrimero(NodoAnos nuevo) 
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
    public NodoAnos eliminarPrimero() 
    {
        NodoAnos nodoEliminado = null;
        
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
    * Metodo que determina si el ano en el cual se esta creando el torneo ya existe.
    * 
    * @param nodAno es el nodo con el ano introducido por el usuario en el cual se desea crear el torneo
    */
    public NodoAnos buscarAnos(NodoAnos nodAno)
    {
        NodoAnos aux = cabeza;
        
        while(aux != null)
        {
            if( nodAno.getAno() == aux.getAno() ) //retorna el nodo registrado si ya existe el ano
            {
                return aux;
            }
            else
            {
                aux = aux.getProximo(); 
            }
        }
        
        return null; //retorna null si no existe el ano
    }
    
    public NodoAnos llenarDialogo(NodoAnos aux)
    {
        if(aux.getAno() == 1)
            aux = cabeza;
        else
            aux = aux.getProximo();
        
        return aux;
    }
    
    //Metodo que cuenta los nodos de la lista
    public int contar()
    {
        NodoAnos aux = cabeza;
        int cont=0;
        
        while(aux != null)
        {
            aux = aux.getProximo();
            cont++;
        }
        return cont;
    }
    
    public ListaAnos leerArchivo(ListaAnos anos) throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ListaAnos lista = anos;
        
        try
        {
            FileInputStream fis = new FileInputStream("lista.DAT");
            ObjectInputStream ois = new ObjectInputStream(fis);
        
            if(ois != null)
            {
                ListaAnos objeto = (ListaAnos) ois.readObject();
                lista = objeto;
            
                ois.close();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No existe un archivo");
        }
        
        return lista;
    }
    
    public void crearArchivo(ListaAnos lista) throws FileNotFoundException, IOException
    {
        FileOutputStream fos = new FileOutputStream("lista.DAT", false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(lista);
        
        oos.flush();
        oos.close();
    }
    
    static final long serialVersionUID = 8925409;
}