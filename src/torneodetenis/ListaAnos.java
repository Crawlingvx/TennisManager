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
 * Esta clase representa la lista de anos donde se crearon torneos. Contiene toda la informacion referente a los torneos en su interior.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 1.04, 16/11/2015
 */
public class ListaAnos implements Serializable
{
    private NodoAnos _cabeza;
    private NodoAnos _cola;
    private ListaEncuentros _encuentros; //lista de Encuentros
    
    public ListaAnos() 
    {
        this._cabeza = null;
    }
    
    public ListaAnos(NodoAnos cabeza)
    {
        this._cabeza = cabeza;
    }

    public NodoAnos getCabeza() {
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
    public void insertarPrimero(NodoAnos nuevo) 
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
    * Metodo buscarAnos
    * Metodo que determina si el ano en el cual se esta creando el torneo ya existe.
    * 
    * @param nodAno es el nodo con el ano introducido por el usuario en el cual se desea crear el torneo
    */
    public NodoAnos buscarAnos(NodoAnos nodAno)
    {
        NodoAnos aux = _cabeza;
        
        while(aux != null)
        {
            if( nodAno.getAno() == aux.getAno() ) //retorna aux si el año ya esta registrado
            {
                return aux;
            }
            else
            {
                aux = aux.getProximo(); 
            }
        }
        
        return null; //retorna null si el año no esta registrado
    }
    
    /**
    * Metodo llenarChoiceDialog
    * Metodo que llena un ChoiceDialog con los años en los que existen torneos.
    * 
    * @param aux nodo para almacenar los años en los que exisen torneos
    */
    public NodoAnos llenarChoiceDialog(NodoAnos aux)
    {
        if(aux.getAno() == 1) //condicion para identificar que auxiliar no contiene valores de la lista de años
            aux = _cabeza;
        else
            aux = aux.getProximo(); //avanza al siguiente nodo
        
        return aux; //devuelve el nodo
    }
    
    /**
    * Metodo leerArchivo
    * Metodo que recupera la informacion del archivo de objetos.
    * 
    * @param anos lista de años donde se almacenara la informacion recuperada del archivo de objetos
    */
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
        catch(FileNotFoundException e) //en caso de que no exista un archivo de objetos
        {
            System.out.println("No existe un archivo");
        }
        
        return lista;
    }
    
    /**
    * Metodo crearArchivo
    * Metodo que sobreescribe el archivo de objetos con una lista de años actualizada.
    * 
    * @param lista lista de años con informacion nueva
    */
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